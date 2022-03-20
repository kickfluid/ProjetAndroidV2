package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class accueil extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button button = (Button) findViewById(R.id.validerpage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retourMain(view);

            }
        });
    }

    public void retourMain(View c){
        Intent intent = new Intent(accueil.this, MapsActivity.class);
        startActivity(intent);
        finish();
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
                Intent myIntent = new Intent(accueil.this, Favoris.class);
                accueil.this.startActivity(myIntent);
                break;

                case R.id.map:
                Intent myMap = new Intent(accueil.this, MapsActivity.class);

                accueil.this.startActivity(myMap);
                break;

        }
        return true;
    }


}