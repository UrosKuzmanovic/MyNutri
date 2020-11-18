package com.epos.mynutri.models;

public class Proizvod {

    private int id, kategorija, kalorije;
    private String naziv;
    private double proteini, uh, masti;

    public Proizvod(int id, int kategorija, int kalorije, String naziv, double proteini, double uh, double masti) {
        this.id = id;
        this.kategorija = kategorija;
        this.kalorije = kalorije;
        this.naziv = naziv;
        this.proteini = proteini;
        this.uh = uh;
        this.masti = masti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKategorija() {
        return kategorija;
    }

    public void setKategorija(int kategorija) {
        this.kategorija = kategorija;
    }

    public int getKalorije() {
        return kalorije;
    }

    public void setKalorije(int kalorije) {
        this.kalorije = kalorije;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getProteini() {
        return proteini;
    }

    public void setProteini(double proteini) {
        this.proteini = proteini;
    }

    public double getUh() {
        return uh;
    }

    public void setUh(double uh) {
        this.uh = uh;
    }

    public double getMasti() {
        return masti;
    }

    public void setMasti(double masti) {
        this.masti = masti;
    }
}
