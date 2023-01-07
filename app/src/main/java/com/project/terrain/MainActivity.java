package com.project.terrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.project.terrain.APISET.Apiset;
import com.project.terrain.Adapter.TerrainAdapter;
import com.project.terrain.Controller.ApiController;
import com.project.terrain.Interfaces.RecyclersView;
import com.project.terrain.model.Position;
import com.project.terrain.model.Terrain;
import com.project.terrain.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity{
    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;
    final String TAG = "addPosition";
    RecyclerView recyclerView;

    ImageView position, home;
    private String imei;
    public User currentUser;
   // SearchView search_barre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.top_places_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        position=findViewById(R.id.position);
        processData();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, new
                LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        altitude = location.getAltitude();
                        accuracy = location.getAccuracy();
                        String msg = String.format(
                                getResources().getString(R.string.new_location), latitude + "",
                                longitude + "", altitude + "", accuracy + "");
                        addPosition(latitude, longitude);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        String newStatus = "";
                        switch (status) {
                            case LocationProvider.OUT_OF_SERVICE:
                                newStatus = "OUT_OF_SERVICE";
                                break;
                            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                                newStatus = "TEMPORARILY_UNAVAILABLE";
                                break;
                            case LocationProvider.AVAILABLE:
                                newStatus = "AVAILABLE";
                                break;
                        }
                        String msg = String.format(getResources().getString(R.string.provider_new_status),
                                provider, newStatus);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        String msg = String.format(getResources().getString(R.string.provider_enabled),
                                provider);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        String msg = String.format(getResources().getString(R.string.provider_disabled),
                                provider);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Position_maps_user.class);
                startActivity(intent);
            }
        });

    }
    void addPosition(final double lat, final double lon) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String deviceId;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceId = Settings.Secure.getString(
                    this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null) {
                deviceId = mTelephony.getDeviceId();
            } else {
                deviceId = Settings.Secure.getString(
                        this.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }
        }
        Call call = ApiController.getInstance()
                .getapi()
                .savePosition(new Position(sdf.format(new Date()),lat, lon,deviceId));

        call.enqueue(new Callback() {


            @Override
            public void onResponse(Call call, Response response) {
                if(!response.isSuccessful()){
                    Log.d(TAG,response.code()+"");
                    return;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }

    public void processData(){
        Call<List<Terrain>> call = ApiController.getInstance()
                .getapi()
                .getAllTerrain();
        call.enqueue(new Callback<List<Terrain>>() {

            @Override
            public void onResponse(Call<List<Terrain>> call, Response<List<Terrain>> response) {
                List<Terrain> data = response.body();
                TerrainAdapter adapter = new TerrainAdapter(MainActivity.this,data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Terrain>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }



}