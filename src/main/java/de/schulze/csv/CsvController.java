package de.schulze.csv;

import de.schulze.csv.assets.*;
import de.schulze.csv.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CsvController
{
    @Autowired
    ILieferantRepositiry lieferantRepositiry;
    @Autowired
    IPaletteRepositiry paletteRepositiry;
    @Autowired
    IArtikelRepositiry artikelRepositiry;
    @Autowired
    ISettingRepositiry settingRepositiry;
    @Autowired
    IUserRepositiry userRepositiry;

    boolean hasLieferanten = true;
    boolean hasPalette = true;

    Builder builder;
    Writer writer = new Writer();
    Cookie cookie = null;


    /**
     * Website Root
     * @param response
     * @param user (Cookie)
     * @return
     */
    @GetMapping("/")
    public String get(HttpServletResponse response, @CookieValue(value = "username", defaultValue = "unknown") String user)
    {
        builder = new Builder(lieferantRepositiry,paletteRepositiry,artikelRepositiry,settingRepositiry);
        System.out.println(user);
        if(user.equalsIgnoreCase("unknown")) builder.addLogin();
        else
        {
            builder.addLieferantAll();
        }
        return builder.get();
    }

    /**
     * Website Search
     * @param response
     * @param user  (Cookie)
     * @param query
     * @return
     */
    @RequestMapping("/{query}")
    public String get(HttpServletResponse response, @CookieValue(value = "username", defaultValue = "unknown") String user, @PathVariable("query") String query)
    {
        String[] queries = query.split("_");

        // Search for "Lieferanten"
        builder = new Builder(lieferantRepositiry,paletteRepositiry,artikelRepositiry,settingRepositiry);
        if(queries.length == 1)
        {
            builder.addLieferant(queries[0]);
            builder.addPaletteAllByLieferant(queries[0]);
        }
        // Search for "Search Query"
        else if(query.startsWith("search_"))
        {
            String[] search = query.split("_");

            List<Lieferant> lieferanten = lieferantRepositiry.findAll();
            List<Palette> paletten = paletteRepositiry.findAll();
            List<Artikel> artikel = artikelRepositiry.findAll();

            ArrayList<Lieferant> listL = new ArrayList();
            ArrayList<Palette> listP = new ArrayList();
            ArrayList<Artikel> listA = new ArrayList();

            // Find "Lieferant" and or "Palette" and or "Artikel" by "Search Query"
            for(String s : search)
            {
                for(Lieferant l : lieferanten) if(l.getLieferantNummer().contains(s) || l.getLieferantName().toLowerCase().contains(s.toLowerCase())) if(!listL.contains(l))listL.add(l);
                for(Palette p : paletten) if(p.getPalettennummer().contains(s)) if(!listP.contains(p))listP.add(p);
                for(Artikel a : artikel) if(a.getArtikelNummer().contains(s) || a.getArtikelBezeichnung().toLowerCase().contains(s.toLowerCase())) if(!listA.contains(a))listA.add(a);
            }

            // Fill with all if No "Lieferant" or "Palette" found
            if(listL.size() == 0)
            {
                listL.addAll(lieferanten);
                hasLieferanten = false;
            }
            if(listP.size() == 0)
            {
                listP.addAll(paletten);
                hasPalette = false;
            }

            /* Bug ? */
            // Set Content relative to Search Result
            for(Lieferant l : listL)
            {
                boolean firstLieferant = true;
                if(hasLieferanten) builder.addLieferant(l.getLieferantNummer());
                for(Palette p : paletteRepositiry.getPalettenByLieferantenID(l.getID()))
                {
                    boolean firstPalette = true;
                    for(Palette p2 : listP)
                    {
                        if(p2.getID() == p.getID())
                        {
                            if(!hasLieferanten && firstLieferant) builder.addLieferant(l.getLieferantNummer());
                            firstLieferant = false;
                            if(hasPalette)builder.addPalette(p.getPalettennummer(), l.getLieferantNummer());

                            for(Artikel a : artikelRepositiry.getArtikelByPalettenID(p.getID()))
                            {
                                for(Artikel a2 : listA)
                                {
                                    if(firstPalette) builder.addPalette(p.getPalettennummer(), l.getLieferantNummer());
                                    firstPalette = false;
                                    if(a2.getID() == a.getID()) builder.addArtikel(a.getArtikelNummer(),p.getID());
                                }
                            }
                        }
                    }
                }
            }
            /* Bug (ende) */

        }
        // Get All
        else
        {
            builder.addLieferant(queries[0]);
            builder.addPalette(queries[1], queries[0]);
            builder.addArtikelAllByPalette(queries[1]);
        }
        return builder.get();
    }

    /**
     * Upload and Save lieferant.csv
     * @param response
     * @param media
     */
    @PostMapping(value = "/lieferant", consumes = "multipart/form-data")
    public void setLieferant(HttpServletResponse response, @RequestParam("lieferant") MultipartFile media)
    {
        try {
            Path filepath = Paths.get("", media.getOriginalFilename());
            media.transferTo(filepath);
            String[][] data = new Decoder().decode(new Loader().loadFile(filepath.toFile()),true);

            if(data.length > 1) for(int i = 1; i < data.length; i++)
            {
                if(lieferantRepositiry.findByLieferantenNummer(data[i][0]) == null)
                    lieferantRepositiry.save(new Lieferant(data[i][0],data[i][1],data[i][2],data[i][3],data[i][4]));
                else
                {
                    lieferantRepositiry.updateLieferantName( data[i][1], data[i][0]);
                    lieferantRepositiry.updateLieferantStraße( data[i][2], data[i][0]);
                    lieferantRepositiry.updateLieferantPLZ( data[i][3], data[i][0]);
                    lieferantRepositiry.updateLieferantORT( data[i][4], data[i][0]);
                }
            }
            else;
        } catch (IOException e) {
            response.setStatus(500);
        }
    }

    /**
     * Upload and Save Paletten.csv
     * @param response
     * @param media
     */
    @PostMapping(value = "/palette", consumes = "multipart/form-data")
    public void setPalette(HttpServletResponse response, @RequestParam("palette") MultipartFile media)
    {
        try {
            Path filepath = Paths.get("", media.getOriginalFilename());
            media.transferTo(filepath);
            String[][] data = new Decoder().decode(new Loader().loadFile(filepath.toFile()),false);

            String lieferantenNummer = media.getOriginalFilename().replace(".csv","");
            int lieferantID = lieferantRepositiry.getLieferantenIDByLieferantenNummer(lieferantenNummer);

            for(Artikel a : artikelRepositiry.findAll()) artikelRepositiry.updateArtikelStat("delete", a.getArtikelNummer(), a.getPalettenID());

            if(data.length > 1) for(int i = 1; i < data.length; i++)
            {
                if(lieferantRepositiry.findByLieferantenNummer(lieferantenNummer) != null) {
                    if(paletteRepositiry.findByPalettenNummer(data[i][0]) == null)
                    {
                        paletteRepositiry.save(new Palette(lieferantID, data[i][0]));
                        artikelRepositiry.save(new Artikel(paletteRepositiry.getPalettenIDByPalettenNummer(data[i][0]), data[i][1], data[i][2], "new"));
                    }
                    if(artikelRepositiry.findByArtikelNummerAndPalettenID(data[i][1], paletteRepositiry.getPalettenIDByPalettenNummer(data[i][0])) == null) artikelRepositiry.save(new Artikel(paletteRepositiry.getPalettenIDByPalettenNummer(data[i][0]), data[i][1], data[i][2], "new"));
                    else
                    {
                        artikelRepositiry.updateArtikelBezeichnung(data[i][2],data[i][1],paletteRepositiry.getPalettenIDByPalettenNummer(data[i][0]));
                        artikelRepositiry.updateArtikelDatum(new Date(),data[i][1],paletteRepositiry.getPalettenIDByPalettenNummer(data[i][0]));
                        artikelRepositiry.updateArtikelStat("new",data[i][1],paletteRepositiry.getPalettenIDByPalettenNummer(data[i][0]));
                    }
                }
                else response.setStatus(500);

                int artikelID = artikelRepositiry.getArtikelIDByArtikelNummerAndPalettenID(data[i][1], paletteRepositiry.getPalettenIDByPalettenNummer(data[i][0]));
                System.out.println(artikelID);
                if(data[i].length > 3) for(int j = 3; j < data[i].length; j++) if(data[i][j].length() != 0)
                {
                    if(settingRepositiry.getSettingsByArtikelIDAndSettingTitle(artikelID, data[0][j]) == null) settingRepositiry.save(new Setting(artikelID, data[0][j], data[i][j]));
                    else settingRepositiry.updateSettingValue(data[i][j], settingRepositiry.getSettingsByArtikelIDAndSettingTitle(artikelID, data[0][j]).getSettingID());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Login User
     * @param response
     * @param name
     * @param password
     */
    @RequestMapping("/login/{name}/{password}")
    public void login(HttpServletResponse response, @PathVariable("name") String name, @PathVariable("password") String password)
    {
        if(userRepositiry.findUser(name,password) != 0)
        {
            cookie = new Cookie("username", name);
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
        }
        else response.setStatus(404);
    }

    /**
     * Export (Download) Data
     * @param response
     * @param query
     */
    @RequestMapping("/export/{query}")
    public void export(HttpServletResponse response, @PathVariable("query") String query)
    {
        // Export All "Lieferanten"
        if(query.equalsIgnoreCase("lieferanten.csv"))
        {
            try {
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; file=lieferanten.csv");
                writer.writeAll(lieferantRepositiry, response.getWriter());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Export "Paletten" relative to "Lieferant"
        else
        {
            try {
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; file=" + query);
                writer.write(lieferantRepositiry, paletteRepositiry, artikelRepositiry, settingRepositiry, query.toLowerCase().replace(".csv",""), response.getWriter());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
