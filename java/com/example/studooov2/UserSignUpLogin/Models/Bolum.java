package com.example.studooov2.UserSignUpLogin.Models;

public class Bolum {

    private String bolumName;
    private String bolumWebLink;

    public Bolum(String bolumName, String bolumWebLink) {
        this.bolumName = bolumName;
        this.bolumWebLink = bolumWebLink;
    }

    public String getBolumName() {
        return bolumName;
    }

    public void setBolumName(String bolumName) {
        this.bolumName = bolumName;
    }

    public String getBolumWebLink() {
        return bolumWebLink;
    }

    public void setBolumWebLink(String bolumWebLink) {
        this.bolumWebLink = bolumWebLink;
    }
}
