package de.schulze.csv.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="palette")
public class Palette
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="palettenID")
    private int ID;

    @Column(name="lieferantenID")
    private int lieferantenID;

    @Column(name="palettennummer")
    private String palettennummer;

    public Palette(){}
    public Palette(int lieferantenID, String nummer)
    {
        this.setLieferantenID(lieferantenID);
        this.setPalettennummer(nummer);
    }
    public Palette(int ID, int lieferantenID, String nummer)
    {
        this.setID(ID);
        this.setLieferantenID(lieferantenID);
        this.setPalettennummer(nummer);
    }


    //-----------


    public int getID() {
        return ID;
    }

    public int getLieferantenID() {
        return lieferantenID;
    }

    public String getPalettennummer() {
        return palettennummer;
    }

//-----------


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLieferantenID(int lieferantenID) {
        this.lieferantenID = lieferantenID;
    }

    public void setPalettennummer(String palettennummer) {
        this.palettennummer = palettennummer;
    }

//-----------


    @Override
    public String toString() {
        return "Palette{" +
                "ID=" + ID +
                ", lieferantenID=" + lieferantenID +
                ", palettennummer='" + palettennummer + '\'' +
                '}';
    }
}
