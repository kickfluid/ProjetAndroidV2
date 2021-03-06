package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class accueil extends AppCompat  {
    TextView messageView;
    Button btnAng, btnFran;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Button button = (Button) findViewById(R.id.validerpage);

        // referencing the text and button views
        messageView = (TextView) findViewById(R.id.Accueil);

        // setting up on click listener event over the button
        // in order to change the language with the help of
        // LocaleHelper class



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

            case R.id.lang:
                Intent mylang = new Intent(accueil.this, Langues.class);

                accueil.this.startActivity(mylang);
                break;

        }
        return true;
    }


}