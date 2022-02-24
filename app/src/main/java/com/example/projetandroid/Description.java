package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Button button = (Button) findViewById(R.id.validerpage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retourMain(view);

            }
        });
    }

    public void retourMain(View c){
        Intent intent = new Intent(Description.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.monmenu, menu);
        return true;
    }
}