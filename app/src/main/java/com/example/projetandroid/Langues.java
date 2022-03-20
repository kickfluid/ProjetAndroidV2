package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Langues extends AppCompat {
    TextView messageView;
    Button btnAng, btnFran;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);
        LanguageManager lang=new LanguageManager(this);
        // referencing the text and button views
        messageView = (TextView) findViewById(R.id.textView);
        btnAng = findViewById(R.id.Ang);
        btnFran = findViewById(R.id.Fran);

        // setting up on click listener event over the button
        // in order to change the language with the help of
        // LocaleHelper class
        btnAng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang.updateResource("en");
                recreate();
            }
        });

        btnFran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang.updateResource("fr");
                recreate();
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
                Intent myIntent = new Intent(Langues.this, Favoris.class);
                Langues.this.startActivity(myIntent);
                break;

            case R.id.acc:
                Intent myacc = new Intent(Langues.this, accueil.class);
                Langues.this.startActivity(myacc);
                break;

            case R.id.map:
                Intent mymap = new Intent(Langues.this, MapsActivity.class);

                Langues.this.startActivity(mymap);
                break;
        }
        return true;
    }
}
