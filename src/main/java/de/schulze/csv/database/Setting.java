package de.schulze.csv.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="setting")
public class Setting
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="settingID")
    private int settingID;

    @Column(name="artikelID")
    private int artikelID;


    @Column(name="settingtitle")
    private String settingTitle;
    @Column(name="settingvalue")
    private String settingValue;

    public Setting(){}
    public Setting(int artikelID, String settingTitle, String settingValue)
    {
        this.setArtikelID(artikelID);
        this.setSettingTitle(settingTitle);
        this.setSettingValue(settingValue);
    }
    public Setting(int ID, int artikelID, String settingTitle, String settingValue)
    {
        this.setSettingID(ID);
        this.setArtikelID(artikelID);
        this.setSettingTitle(settingTitle);
        this.setSettingValue(settingValue);
    }


    //-----------

    public int getArtikelID() {
        return artikelID;
    }

    public int getSettingID() {
        return settingID;
    }

    public String getSettingTitle() {
        return settingTitle;
    }

    public String getSettingValue() {
        return settingValue;
    }


    //-----------

    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public void setSettingTitle(String settingTitle) {
        this.settingTitle = settingTitle;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }


    //-----------


    @Override
    public String toString() {
        return "Setting{" +
                "artikelID=" + artikelID +
                ", settingID=" + settingID +
                ", settingTitle='" + settingTitle + '\'' +
                ", settingValue='" + settingValue + '\'' +
                '}';
    }
}
