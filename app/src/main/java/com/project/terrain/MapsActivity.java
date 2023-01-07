package com.project.terrain;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.terrain.APISET.Apiset;
import com.project.terrain.Controller.ApiController;
import com.project.terrain.databinding.ActivityMapsBinding;
import com.project.terrain.model.Terrain;
import java.lang.Long;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    String id;

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
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        Call<Terrain> call = ApiController.getInstance()
                .getapi()
                .getTerrainById(Long.parseLong(getIntent().getStringExtra("id")));
        call.enqueue(new Callback<Terrain>() {
            @Override
            public void onResponse(Call<Terrain> call, Response<Terrain> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, response.code() + "");
                    return;
                }
                    Terrain c = response.body();
                    mMap.addMarker(new MarkerOptions().position(new
                            LatLng(parseDouble(c.getLat()),
                            parseDouble(c.getLongitude()))).title(c.getNom()));

            }

            @Override
            public void onFailure(Call<Terrain> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }


            // Add a marker in Sydney and move the camera

        });
    }
}