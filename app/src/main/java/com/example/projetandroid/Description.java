package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

public class Description extends AppCompatActivity {
    private ListView lv;
    public AdapterP adapter;
    public ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ImageButton fav = (ImageButton) findViewById(R.id.favoris);
        lv = findViewById(R.id.edittext);
        arrayList = new ArrayList<String>();
        adapter = new AdapterP(this, arrayList);
        fav.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String uniqueId = UUID.randomUUID().toString();
                Toast.makeText(Description.this, "Clic", Toast.LENGTH_SHORT).show();
                Log.d(Favoris.TAG, "Click");
                lv.setAdapter(Favoris.adapter);
                Favoris.arrayList.add(uniqueId);
                Favoris.adapter.notifyDataSetChanged();

            }


        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.monmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item1) {
        switch (item1.getItemId()) {
            case R.id.fav:
                Intent myIntent = new Intent(Description.this, Favoris.class);
                Description.this.startActivity(myIntent);
                break;

            case R.id.acc:
                Intent myacc = new Intent(Description.this, accueil.class);
                Description.this.startActivity(myacc);
                break;
        }
        return true;
    }


}