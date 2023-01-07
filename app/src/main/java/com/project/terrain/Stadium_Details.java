package com.project.terrain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.terrain.model.Terrain;

public class Stadium_Details extends AppCompatActivity {

    TextView name_view,star,address,description,tarif,id_terrain;
    ImageView button, stadium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_details);



        name_view=findViewById(R.id.stadium_name);
        address=findViewById(R.id.stadium_address);
        tarif=findViewById(R.id.stadium_tarif);
        description=findViewById(R.id.description);
        star=findViewById(R.id.rate);
        button= findViewById(R.id.button_location);
        id_terrain= findViewById(R.id.id_terrain);
        stadium=findViewById(R.id.stadium_image);
        //fetch data

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String nom = intent.getStringExtra("name");
        String adresse = intent.getStringExtra("address");
        String rent = intent.getStringExtra("rental");
        String about = intent.getStringExtra("about");
        String rank = intent.getStringExtra("rate");
        String image = intent.getStringExtra("image");

        name_view.setText(nom);
        address.setText(adresse);
        tarif.setText(rent);
        description.setText(about);
        star.setText(rank);

        Glide.with(this).load(image).into(stadium);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }
}