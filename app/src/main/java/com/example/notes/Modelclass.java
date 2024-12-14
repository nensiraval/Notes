package com.example.notes;

import java.io.Serializable;

public class Modelclass implements Serializable {

      public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }



    String Tittle;

    public Modelclass(String tittle, String discription, int id) {
        Tittle = tittle;
        Discription = discription;
        this.id = id;
    }

    String Discription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
}
