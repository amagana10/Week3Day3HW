package com.example.week3day3hw;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    AnimalDBHelper animalDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animalDBHelper = new AnimalDBHelper(this);

        recyclerView = findViewById(R.id.rvRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(animalDBHelper.getAllFromDB());
        recyclerView.setAdapter(myRecyclerViewAdapter);


        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(myRecyclerViewAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        deleteAnimals();
    }

    public void deleteAnimals() {
        if (myRecyclerViewAdapter.getAnimalsDeleted()!=null){
            for (Animal animal: myRecyclerViewAdapter.getAnimalsDeleted()) {
                String AnimalIdToDelete = String.valueOf(animal.getAnimalID());
                String[] idsToDelete = new String[]{AnimalIdToDelete};
                Log.d("TAG", "onClick: DELETING " + AnimalIdToDelete);
                animalDBHelper.deleteFromDatabaseById(idsToDelete);
            }
            myRecyclerViewAdapter.setAnimalsDeleted(new ArrayList<Animal>());
        }

    }

    public void onClick(View view) {
        Intent intent = new Intent(this,EnterAnimalData.class);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data !=null){
            Bundle bundle = data.getExtras();
            if (bundle!=null){
                Animal returnAnimal = bundle.getParcelable("new_animal");
                if (returnAnimal!=null){
                    animalDBHelper.insertIntoDB(returnAnimal);
                    myRecyclerViewAdapter.addAnimalToList(returnAnimal);
                }
            }

        }
    }
}
