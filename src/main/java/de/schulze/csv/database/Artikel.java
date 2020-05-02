package de.schulze.csv.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Artikel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="artikelID")
    private int ID;
    @Column(name="palettenID")
    private int palettenID;

    @Column(name="artikelnummer")
    private String artikelNummer;
    @Column(name="artikelbezeichnung")
    private String artikelbezeichnung;
    @Column(name="datum")
    private Date datum;
    @Column(name="stat")
    private String stat;

    public Artikel(){}
    public Artikel(int palettenID, String artikel, String bezeichnung, String stat)
    {
        this.setPalettenID(palettenID);
        this.setArtikelNummer(artikel);
        this.setArtikelBezeichnung(bezeichnung);
        this.setDatum(new Date());
        this.setStat(stat);
    }
    public Artikel(int ID, int palettenID, String artikel, String bezeichnung, String stat)
    {
        this.setID(ID);
        this.setPalettenID(palettenID);
        this.setArtikelNummer(artikel);
        this.setArtikelBezeichnung(bezeichnung);
        this.setDatum(new Date());
        this.setStat(stat);
    }


    //-----------

    public int getID() {
        return ID;
    }

    public int getPalettenID() {
        return palettenID;
    }

    public String getArtikelNummer() {
        return artikelNummer;
    }


    public String getArtikelBezeichnung() {
        return artikelbezeichnung;
    }


    public Date getDatum() {
        return datum;
    }

    public String getStat() {
        return stat;
    }



    //-----------


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPalettenID(int palettenID) {
        this.palettenID = palettenID;
    }

    public void setArtikelNummer(String artikelNummer) {
        this.artikelNummer = artikelNummer;
    }


    public void setArtikelBezeichnung(String artikelBezeichnung) {
        this.artikelbezeichnung = artikelBezeichnung;
    }


    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }


}
