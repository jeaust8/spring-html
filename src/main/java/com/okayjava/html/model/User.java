package com.okayjava.html.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class User {

    //primary key
    @Id
    @GeneratedValue
    @Column(name = "SN")
    private int sn;

    @Column(name = "fristname")
    private String fname;
    @Column(name = "lastname")
    private String lname;
    @Column(name = "email")
    private String email;
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



    @Override
    public String toString() {
        return "User{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
