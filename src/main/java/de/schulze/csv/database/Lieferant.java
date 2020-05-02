package de.schulze.csv.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="lieferant")
public class Lieferant
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="lieferantenID")
    private int ID;

    @Column(name="lieferantennummer")
    private String lieferantNummer;
    @Column(name="lieferantenname")
    private String lieferantName;
    @Column(name="strasse")
    private String lieferantStraße;
    @Column(name="plz")
    private String lieferantPLZ;
    @Column(name="ort")
    private String lieferantOrt;

    public Lieferant(){}
    public Lieferant(String nummer, String name, String straße, String plz, String ort)
    {
        this.setLieferantNummer(nummer);
        this.setLieferantName(name);
        this.setLieferantStraße(straße);
        this.setLieferantPLZ(plz);
        this.setLieferantOrt(ort);
    }
    public Lieferant(int ID, String nummer, String name, String straße, String plz, String ort)
    {
        this.setID(ID);
        this.setLieferantNummer(nummer);
        this.setLieferantName(name);
        this.setLieferantStraße(straße);
        this.setLieferantPLZ(plz);
        this.setLieferantOrt(ort);
    }


    //-----------


    public int getID() {
        return ID;
    }

    public String getLieferantNummer() {
        return lieferantNummer;
    }

    public String getLieferantName() {
        return lieferantName;
    }

    public String getLieferantStraße() {
        return lieferantStraße;
    }

    public String getLieferantPLZ() {
        return lieferantPLZ;
    }

    public String getLieferantOrt() { return lieferantOrt; }



    //-----------


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLieferantNummer(String lieferantNummer) {
        this.lieferantNummer = lieferantNummer;
    }

    public void setLieferantName(String lieferantName) {
        this.lieferantName = lieferantName;
    }

    public void setLieferantStraße(String lieferantStraße) {
        this.lieferantStraße = lieferantStraße;
    }

    public void setLieferantPLZ(String lieferantPLZ) {
        this.lieferantPLZ = lieferantPLZ;
    }

    public void setLieferantOrt(String lieferantOrt) {
        this.lieferantOrt = lieferantOrt;
    }


    //-----------


    @Override
    public String toString() {
        return "Lieferant{" +
                "ID=" + ID +
                ", lieferantNummer='" + lieferantNummer + '\'' +
                ", lieferantName='" + lieferantName + '\'' +
                ", lieferantStraße='" + lieferantStraße + '\'' +
                ", lieferantPLZ='" + lieferantPLZ + '\'' +
                ", lieferantOrt='" + lieferantOrt + '\'' +
                '}';
    }
}
