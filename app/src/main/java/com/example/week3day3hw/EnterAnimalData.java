package com.example.week3day3hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class EnterAnimalData extends AppCompatActivity {
    Intent passedIntent;
    EditText etName;
    EditText etSound;
    ListView lstOfTypes;
    String selectedtype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_animal_data);

        passedIntent = getIntent();

        etName = findViewById(R.id.etName);
        etSound = findViewById(R.id.etSound);
        lstOfTypes = findViewById(R.id.lstAnimalTypes);

        final ArrayList<String> types = new ArrayList<>();
        types.add("Mamals");
        types.add("Reptiles");
        types.add("Fish");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,types);

        lstOfTypes.setAdapter(arrayAdapter);

        lstOfTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedtype = types.get(position);
            }
        });
    }

    public void onClick(View view) {
        Animal send = new Animal();
        String name = etName.getText()!=null?etName.getText().toString():"";
        String sound = etSound.getText()!=null?etSound.getText().toString():"";
        String type = selectedtype!=null?selectedtype:"";

        send.setName(name);
        send.setSound(sound);
        send.setType(type);

        Bundle bundle = new Bundle();
        bundle.putParcelable("new_animal",send);
        passedIntent.putExtras(bundle);
        setResult(101,passedIntent);
        finish();
    }
}
