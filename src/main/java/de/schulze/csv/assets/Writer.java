package de.schulze.csv.assets;

import de.schulze.csv.database.*;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Writer
{
    /**
     * Write all "Lieferant" data in CSV-Format to File
     * @param lieferantRepositiry
     * @param writer
     * @throws IOException
     */
    public void writeAll(ILieferantRepositiry lieferantRepositiry, PrintWriter writer) throws IOException {
        writer.write("Lieferantennummer,Lieferantenname,Straße,PLZ,Ort\n");

        for(Lieferant l : lieferantRepositiry.findAll())
        {
            writer.write(l.getLieferantNummer());
            writer.write("\"" + l.getLieferantName() + "\",");
            writer.write("\"" + l.getLieferantStraße() + "\",");
            writer.write(l.getLieferantPLZ() + ",");
            writer.write("\"" + l.getLieferantOrt() + "\"\n");
        }
    }

    /**
     * Write all "Palette" data Relative to "Lieferant" in CSV-Format to File
     * @param lieferantRepositiry
     * @param writer
     * @throws IOException
     */
    public void write(ILieferantRepositiry lieferantRepositiry, IPaletteRepositiry paletteRepositiry,
                      IArtikelRepositiry artikelRepositiry, ISettingRepositiry settingRepositiry,
                      String lieferantenNummer, PrintWriter writer) throws IOException {
        ArrayList<String> settingsListTitle = new ArrayList<>();
        ArrayList<Setting> settingsList = new ArrayList<>();
        writer.write("Palette,Artikelnummer,Artikelbezeichnung");
        for(Setting s : settingRepositiry.findAll())
        {
            if(!settingsListTitle.contains(s.getSettingTitle()))
            {
                settingsListTitle.add(s.getSettingTitle());
                settingsList.add(s);
                writer.write("," + s.getSettingTitle());
            }
        }

        System.out.println(settingsListTitle.size());

        writer.write("\n");

        System.out.println(lieferantenNummer);
        List<Palette> paletten = paletteRepositiry.getPalettenByLieferantenID(lieferantRepositiry.findByLieferantenNummer(lieferantenNummer).getID());
        for(Palette p : paletten)
        {
            for(Artikel a : artikelRepositiry.getArtikelByPalettenID(p.getID()))
            {
                writer.write(p.getPalettennummer() + ",");
                writer.write(a.getArtikelNummer() + ",");
                writer.write( "\"" + a.getArtikelBezeichnung() + "\"");

                for(Setting s : settingsList)
                {
                    if(settingRepositiry.getSettingsByArtikelIDAndSettingTitle(a.getID(),s.getSettingTitle()) != null) writer.write(",\"" + s.getSettingValue() + "\"");
                    else writer.write(",");
                }

                writer.write("\n");
            }
        }
    }
}
