package com.project.terrain.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Position {


    @SerializedName("id")
    private int id;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("date")
    private String date;

    @SerializedName("imei")
    private String imei;

    public Position( String date ,double latitude, double longitude, String imei) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.imei = imei;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
