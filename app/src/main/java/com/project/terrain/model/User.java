package com.project.terrain.model;

public class User {
    private long id;

    private String imei;


    public User(long id, String imei) {
        this.id = id;

        this.imei = imei;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
