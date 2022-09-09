package com.example.kursinis;

import java.io.Serializable;

public class Item implements Serializable {

    private boolean isChecked = false;
    private String Angliavandeniai, Baltymai, Kalorijos, Maisto_prod, Riebalai;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getAngliavandeniai() {
        return Angliavandeniai;
    }

    public String getBaltymai() {
        return Baltymai;
    }

    public String getKalorijos() {
        return Kalorijos;
    }

    public String getMaisto_prod() {
        return Maisto_prod;
    }

    public String getRiebalai() {
        return Riebalai;
    }

    public void setAngliavandeniai(String angliavandeniai) {
        Angliavandeniai = angliavandeniai;
    }

    public void setBaltymai(String baltymai) {
        Baltymai = baltymai;
    }

    public void setKalorijos(String kalorijos) {
        Kalorijos = kalorijos;
    }

    public void setMaisto_prod(String maisto_prod) {
        Maisto_prod = maisto_prod;
    }

    public void setRiebalai(String riebalai) {
        Riebalai = riebalai;
    }
}