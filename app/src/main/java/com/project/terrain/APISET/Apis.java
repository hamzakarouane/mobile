package com.project.terrain.APISET;

public class Apis {
    public static final String URL_001="http://192.168.1.104:8087/terrains/";

    public static TerrainService getTerrainService(){
        return  Cliente.getClient(URL_001).create(TerrainService.class);
    }
}
