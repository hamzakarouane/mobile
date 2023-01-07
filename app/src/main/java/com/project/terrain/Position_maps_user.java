package com.project.terrain;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.terrain.APISET.Apiset;
import com.project.terrain.Controller.ApiController;
import com.project.terrain.databinding.ActivityMapsBinding;
import com.project.terrain.model.Position;
import com.project.terrain.model.Terrain;
import com.project.terrain.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Position_maps_user extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        final String TAG = "addPosition";
        mMap = googleMap;
            Call<List<Position>> call = ApiController.getInstance()
                    .getapi()
                    .findAll();
        call.enqueue(new Callback<List<Position>>() {

            @Override
            public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, response.code() + "");
                    return;
                }
                for(Position c:response.body()){
                    mMap.addMarker(new MarkerOptions().position(new
                            LatLng(c.getLatitude(),
                            c.getLongitude())).title(String.valueOf(c.getId())));
                }
            }

            @Override
            public void onFailure(Call<List<Position>> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });

        // Add a marker in Sydney and move the camera


    }
}