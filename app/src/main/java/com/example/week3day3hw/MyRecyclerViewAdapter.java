package com.example.week3day3hw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
               implements ItemTouchHelperAdapter {
    ArrayList<Animal> animals;
    ArrayList<Animal> animalsDeleted;

    public MyRecyclerViewAdapter(ArrayList<Animal> animals) {
        this.animals = animals;
        animalsDeleted = new ArrayList<Animal>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.animal, viewGroup, false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Animal currentAnimalBeingPopulted = animals.get(i);
        viewHolder.tvAnimalType.setText(currentAnimalBeingPopulted.getType());
        viewHolder.tvAnimalName.setText(currentAnimalBeingPopulted.getName());
        viewHolder.tvAnimalSound.setText(currentAnimalBeingPopulted.getSound());
        viewHolder.tvAnimalImage.setText(currentAnimalBeingPopulted.getImage());
        Log.d("TAG", "onBindViewHolder: item being rendered = " + i);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("animal",animals.get(i));
                Intent intentToStartDetails = new Intent(v.getContext(),Details.class);
                intentToStartDetails.putExtras(bundle);
                v.getContext().startActivity(intentToStartDetails);

            }
        });

    }
    public void addAnimalToList(Animal animal){
        animals.add(animal);
        notifyDataSetChanged();
    }

    public ArrayList<Animal> getAnimalsDeleted() {
        return animalsDeleted;
    }

    public void setAnimalsDeleted(ArrayList<Animal> animalsDeleted) {
        this.animalsDeleted = animalsDeleted;
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    @Override
    public void onItemDismiss(int position) {
        Animal animalToremove = animals.get(position);
        animalsDeleted.add(animalToremove);
        animals.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(animals, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(animals, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAnimalType;
        TextView tvAnimalName;
        TextView tvAnimalSound;
        TextView tvAnimalImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnimalType = itemView.findViewById(R.id.tvAnimalType);
            tvAnimalName = itemView.findViewById(R.id.tvAnimalName);
            tvAnimalSound = itemView.findViewById(R.id.tvAnimalSound);
            tvAnimalImage = itemView.findViewById(R.id.tvAnimalImage);

        }
    }
}
