package com.example.week3day3hw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class Details extends AppCompatActivity {
    Intent passedIntent;
    Animal animal;
    TextView tvAnimalPop;
    TextView tvAnimalType;
    TextView tvAnimalName;
    TextView tvAnimalSound;
    TextView tvAnimalImage;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvAnimalType = findViewById(R.id.tvdetAnimalType);
        tvAnimalName = findViewById(R.id.tvdetAnimalName);
        tvAnimalSound = findViewById(R.id.tvdetAnimalSound);
        tvAnimalImage = findViewById(R.id.tvdetAnimalImage);
        tvAnimalPop = findViewById(R.id.tvAnimalPop);

        passedIntent = getIntent();
        Bundle bundle = passedIntent.getExtras();
        animal = bundle.getParcelable("animal");

        tvAnimalPop.setText("Population: " +String.valueOf(new Random().nextInt(10000)));
        tvAnimalType.setText("Type: "+animal.getType());
        tvAnimalName.setText("Name: " +animal.getName());
        tvAnimalSound.setText("Sound: "+animal.getSound());
        tvAnimalImage.setText(animal.getImage());
    }
}
