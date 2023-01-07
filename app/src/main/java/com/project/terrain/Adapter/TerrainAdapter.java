package com.project.terrain.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.project.terrain.Interfaces.RecyclersView;
import com.project.terrain.R;
import com.project.terrain.Stadium_Details;
import com.project.terrain.model.Terrain;

import java.util.List;

public class TerrainAdapter extends RecyclerView.Adapter<TerrainAdapter.ViewHolder> {

    List<Terrain> stadiums;

    Context context;

    RecyclersView recyclersView;

    public TerrainAdapter(Context context, List<Terrain> stadiums) {
        this.context=context;
        this.stadiums = stadiums;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_places_row_item,parent,false);
        return new ViewHolder(view);
    }

    public void setFilteredList(List<Terrain> filteredList) {
        this.stadiums = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stadium.setText(stadiums.get(position).getNom());
        holder.type.setText(stadiums.get(position).getType());
        holder.price.setText(stadiums.get(position).getTarif()+" DH");

        holder.id.setText(stadiums.get(position).getId()+"");
        Glide.with(holder.stadium.getContext()).load(stadiums.get(position).getPhoto()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Stadium_Details.class);
                //send data
                intent.putExtra("id",stadiums.get(position).getId()+"");
                intent.putExtra("name",stadiums.get(position).getNom());
                intent.putExtra("address",stadiums.get(position).getAddress());
                intent.putExtra("rental",stadiums.get(position).getTarif());
                intent.putExtra("lat",stadiums.get(position).getLat());
                intent.putExtra("long",stadiums.get(position).getLongitude());
                intent.putExtra("rate",stadiums.get(position).getRank());
                intent.putExtra("about",stadiums.get(position).getDescription());
                intent.putExtra("image",stadiums.get(position).getPhoto());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stadiums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView stadium;
        TextView type;
        TextView price;
        TextView id;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stadium=itemView.findViewById(R.id.place_name);
            type=itemView.findViewById(R.id.country_name);
            price=itemView.findViewById(R.id.price);
            id=itemView.findViewById(R.id.id_terrain);
            image=itemView.findViewById(R.id.place_image);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position= getAdapterPosition();
            Toast.makeText(context,"position"+position,Toast.LENGTH_LONG).show();
            Intent intent=new Intent(context, Stadium_Details.class);
           //send data
            intent.putExtra("id",stadiums.get(position).getId());

            context.startActivity(intent);
        }
    }

}
