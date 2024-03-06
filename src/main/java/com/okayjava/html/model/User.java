package com.okayjava.html.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "userid")
    private Long userId;

    @Column(name = "firstname")
    private String fname;
    @Column(name = "lastname")
    private String lname;
    @Column(name = "email")
    private String email;
    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name= "zipcode")
    private String zipcode;

    public Long getUserId(){
        return userId;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userId + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", zipcode'" + zipcode + '\'' +
                '}';
    }
}
