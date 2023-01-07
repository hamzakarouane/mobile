package com.project.terrain.model;

public class Terrain {
    private int id;
    private String address;
    private String description;
    private String etat;
    private  String lat;
    private String longitude;
    private String nom;
    private String rank;
    private String sol;
    private String tarif;
    private String type;
    private String photo;

    public Terrain(int id, String address, String description, String etat, String lat, String longitude, String nom, String rank, String sol, String tarif, String type, String photo) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.etat = etat;
        this.lat = lat;
        this.longitude = longitude;
        this.nom = nom;
        this.rank = rank;
        this.sol = sol;
        this.tarif = tarif;
        this.type = type;
        this.photo=photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSol() {
        return sol;
    }

    public void setSol(String sol) {
        this.sol = sol;
    }

    public Terrain() {
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}