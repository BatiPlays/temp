package de.schulze.csv.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="userID")
    private int ID;

    @Column(name="userName")
    private String userName;
    @Column(name="userPassword")
    private String userPassword;

    public User(){}
    public User(String name, String password)
    {
        this.setUserName(name);
        this.setUserPassword(password);
    }
    public User(int ID, String name, String password)
    {
        this.setID(ID);
        this.setUserName(name);
        this.setUserPassword(password);
    }


    //-----------

    public int getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }


    //-----------


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
