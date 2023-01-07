package com.project.terrain.model;

public class Ville {
    private int id;
    private String nom;

    public Ville(int id, String ville) {
        this.id = id;
        this.nom = ville;
    }

    public Ville() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String ville) {
        this.nom = ville;
    }
}
