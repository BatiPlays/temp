package de.schulze.csv.assets;

import de.schulze.csv.database.*;

public class Builder
{
    ILieferantRepositiry lieferantRepositiry;
    IPaletteRepositiry paletteRepositiry;
    IArtikelRepositiry artikelRepositiry;
    ISettingRepositiry settingRepositiry;

    StringBuilder builder = new StringBuilder();
    boolean content = false;

    /**
     * Built HTML Site
     * @param lieferantRepositiry
     * @param paletteRepositiry
     * @param artikelRepositiry
     * @param settingRepositiry
     */
    public Builder(ILieferantRepositiry lieferantRepositiry, IPaletteRepositiry paletteRepositiry, IArtikelRepositiry artikelRepositiry, ISettingRepositiry settingRepositiry)
    {
        this.lieferantRepositiry = lieferantRepositiry;
        this.paletteRepositiry = paletteRepositiry;
        this.artikelRepositiry = artikelRepositiry;
        this.settingRepositiry = settingRepositiry;

        builder.append("<!DOCTYPE html>\n");
        builder.append("<html>\n");
        builder.append("<head>\n");
        builder.append("<title>CSV - Datenbank</title>\n");
        builder.append("<link rel=\"stylesheet\" href=\"css/main.css\">\n");
        builder.append("</head>\n");
        builder.append("<body>\n");

        builder.append("<div class=\"head\">Suche:\n");
        builder.append("<input id=\"input\" type=\"text\"></input>\n");
        builder.append("</div>\n");

        builder.append("<p id=\"search\" class=\"search\">Suchen</p>\n");

        builder.append("<div id=\"export_all\" data=\"\\\" class=\"card_export\">\n");
        builder.append("<div class=\"title\">\n");
        builder.append("<h3>Export Lieferanten</h3>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");

        builder.append("<div data=\"\\\" class=\"card\">\n");
        builder.append("<div class=\"title\">\n");
        builder.append("<h3>Alle Lieferanten</h3>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");

        builder.append("<div class=\"addLieferant\">\n");
        builder.append("<p id=\"lieferantClick\">Lieferant Hinzufügen/Ändern</p>\n");
        builder.append("</div>\n");
        builder.append("<div class=\"addArtikel\">\n");
        builder.append("<p id=\"artikelClick\">Artikel Hinzufügen/Ändern</p>\n");
        builder.append("</div>\n");
    }


    /**
     * Add all "Lieferanten"
     */
    public void addLieferantAll()
    {
        content = true;
        for(Lieferant l : lieferantRepositiry.findAll())
        {
            builder.append("<div data=\"" + l.getLieferantNummer() + "\" class=\"card\">\n");
            builder.append("<div class=\"title\">\n");
            builder.append("<h3>Lieferant: " + l.getLieferantName() + "</h3>\n");
            builder.append("<p>Adresse: " + l.getLieferantStraße() + ", " + l.getLieferantPLZ() + " " + l.getLieferantOrt() + "</p>\n");
            builder.append("<p>Nummer: " + l.getLieferantNummer() + "</p>\n");
            builder.append("</div>\n");
            builder.append("<div class=\"menu\">\n");
            builder.append("<p>Paletten: " + lieferantRepositiry.getPalettenByLieferantenNummer(l.getLieferantNummer()).size() + "</p>\n");
            builder.append("<p class=\"right\">Klicken für mehr Informationen</p>\n");
            builder.append("</div>\n");
            builder.append("</div>\n");
        }
    }

    /**
     * Add single "Lieferant" by "Lieferantennummer"
     * @param nummer
     */
    public void addLieferant(String nummer)
    {
        content = true;
        Lieferant l = lieferantRepositiry.findByLieferantenNummer(nummer);

        builder.append("<div id=\"export_palette\" data=\"" + l.getLieferantNummer() + "\" class=\"card_export\">\n");
        builder.append("<div class=\"title\">\n");
        builder.append("<h3>Export " + l.getLieferantName() + "</h3>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");

        builder.append("<div data=\"" + l.getLieferantNummer() + "\" class=\"card\">\n");
        builder.append("<div class=\"title\">\n");
        builder.append("<h3>Lieferant: " + l.getLieferantName() + "</h3>\n");
        builder.append("<p>Adresse: " + l.getLieferantStraße() + ", " + l.getLieferantPLZ() + " " + l.getLieferantOrt() + "</p>\n");
        builder.append("<p>Nummer: " + l.getLieferantNummer() + "</p>\n");
        builder.append("</div>\n");
        builder.append("<div class=\"menu\">\n");
        builder.append("<p>Paletten: " + lieferantRepositiry.getPalettenByLieferantenNummer(l.getLieferantNummer()).size() + "</p>\n");
        builder.append("<p class=\"right\">Klicken für mehr Informationen</p>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");
    }

    /**
     * Add all "Paletten" by "Lieferantennummer"
     * @param nummer
     */
    public void addPaletteAllByLieferant(String nummer)
    {
        content = true;
        for( Palette p : paletteRepositiry.getPalettenByLieferantenID(lieferantRepositiry.getLieferantenIDByLieferantenNummer(nummer)))
        {
            builder.append("<div data=\"" + nummer + "_" + p.getPalettennummer() + "\" class=\"card_palette\">\n");
            builder.append("<div class=\"title\">\n");
            builder.append("<h3>Palette: " + p.getPalettennummer() + "</h3>\n");
            builder.append("</div>\n");
            builder.append("<div class=\"menu\">\n");
            builder.append("<p>Artikel: " + artikelRepositiry.getArtikelByPalettenID(paletteRepositiry.findByPalettenNummer(p.getPalettennummer()).getID()).size() + "</p>\n");
            builder.append("<p class=\"right\">Klicken für mehr Informationen</p>\n");
            builder.append("</div>\n");
            builder.append("</div>\n");
        }
    }

    /**
     * Add Single "Palette" by "Lieferantennummer" and "Palettennummer"
     * @param nummer
     */
    public void addPalette(String nummer, String lieferant)
    {
        content = true;
        Palette p = paletteRepositiry.findByPalettenNummer(nummer);

        builder.append("<div data=\"" + lieferant + "_" + p.getPalettennummer() + "\" class=\"card_palette\">\n");
        builder.append("<div class=\"title\">\n");
        builder.append("<h3>Palette: " + p.getPalettennummer() + "</h3>\n");
        builder.append("</div>\n");
        builder.append("<div class=\"menu\">\n");
        builder.append("<p>Artikel: " + artikelRepositiry.getArtikelByPalettenID(paletteRepositiry.findByPalettenNummer(p.getPalettennummer()).getID()).size() + "</p>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");
    }

    /**
     * Add all "Artikel" by "Palettenummer"
     * @param nummer
     */
    public void addArtikelAllByPalette(String nummer)
    {
        content = true;
        for(Artikel a : artikelRepositiry.getArtikelByPalettenID(paletteRepositiry.findByPalettenNummer(nummer).getID()))
        {
            if(a.getStat().equalsIgnoreCase("delete")) builder.append("<div data=\"artikel_" + a.getID() + "\" class=\"card_artikel_delete\">\n");
            else builder.append("<div data=\"artikel_" + a.getID() + "\" class=\"card_artikel\">\n");
            builder.append("<div class=\"title\">\n");
            builder.append("<h3>Artikel: " + a.getArtikelBezeichnung() + "</h3>\n");
            builder.append("</div>\n");
            builder.append("<p>Nummer: " + a.getArtikelNummer() + "</p>\n");
            builder.append("<p>Datum: " + a.getDatum() + "</p>\n");
            builder.append("<p>Stat: " + a.getStat() + "</p>\n");
            for(Setting s : settingRepositiry.getSettingsByArtikelID(artikelRepositiry.getArtikelIDByArtikelNummerAndPalettenID(a.getArtikelNummer(), paletteRepositiry.findByPalettenNummer(nummer).getID())))
                builder.append("<p>" + s.getSettingTitle() + ": " + s.getSettingValue() + "</p>\n");
            builder.append("</div>\n");
        }
    }

    /**
     * Add Single "Artikel" by "Artikelnummer" and "PalettenID"
     * @param nummer
     */
    public void addArtikel(String nummer, int palette)
    {
        content = true;
        Artikel a = artikelRepositiry.findByArtikelNummerAndPalettenID(nummer,palette);

        builder.append("<div data=\"artikel_" + a.getID() + "\" class=\"card_artikel\">\n");
        builder.append("<div class=\"title\">\n");
        builder.append("<h3>Artikel: " + a.getArtikelBezeichnung() + "</h3>\n");
        builder.append("</div>\n");
        builder.append("<p>Nummer: " + a.getArtikelNummer() + "</p>\n");
        builder.append("<p>Datum: " + a.getDatum() + "</p>\n");
        builder.append("<p>Stat: " + a.getStat() + "</p>\n");
        for(Setting s : settingRepositiry.getSettingsByArtikelID(a.getID()))
            builder.append("<p>" + s.getSettingTitle() + ": " + s.getSettingValue() + "</p>\n");
        builder.append("</div>\n");
    }

    /**
     * Add "Login" if you didn't logged in
     */
    public void addLogin()
    {
        builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n");
        builder.append("<html>\n");
        builder.append("<head>\n");
        builder.append("<title>CSV - Datenbank</title>\n");
        builder.append("<link rel=\"stylesheet\" href=\"css/main.css\">\n");
        builder.append("</head>\n");
        builder.append("<body>\n");

        builder.append("<div>\n");
        builder.append("<input id=\"name\" type=\"text\"></input>\n");
        builder.append("<input id=\"password\" type=\"password\"></input>\n");
        builder.append("<p id=\"button\">Login</p>\n");
        builder.append("</div>\n");

        builder.append("<script text=\"text/javascript\" src=\"js/login.js\"></script>\n");
        builder.append("</body>\n");
        builder.append("</html>\n");
    }

    public String get()
    {
        if(!content)
        {
            builder.append("<div class=\"card_empty\">\n");
            builder.append("<div class=\"title\">\n");
            builder.append("<h3>Kein Passenden Inhalt gefunden</h3>\n");
            builder.append("</div>\n");
            builder.append("</div>\n");
        }
        builder.append("<script text=\"text/javascript\" src=\"js/main.js\"></script>\n");
        builder.append("</body>\n");
        builder.append("</html>\n");

        return builder.toString();
    }
}
