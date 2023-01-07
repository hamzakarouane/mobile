package com.project.terrain.APISET;

import com.project.terrain.model.Terrain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TerrainService {

    @GET("getTerrains")
    Call<List<Terrain>> getTerrains();

    @POST("addTerrain")
    Call<Terrain> addTerrain(@Body Terrain terrain);

    @DELETE
    Call<Terrain> deleteTerrain(@Path("id") long id);
}
