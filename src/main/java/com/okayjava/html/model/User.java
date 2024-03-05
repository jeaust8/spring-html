package com.okayjava.html.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "SN")
    private int sn;

    @Column(name = "firstname")
    private String fname;
    @Column(name = "lastname")
    private String lname;
    @Column(name = "email")
    private String email;
    @Column(name = "phonenumber")
    private String phonenumber;

    public int getSn(){
        return sn;
    }
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "sn='" + sn + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
