package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projetandroid.R;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.UUID;

public class Favoris extends AppCompatActivity {
    public static AbstractCollection<String> arrayList;
    public static ArrayAdapter<Object> adapter;
    private View baseView;
    private ListView lv;
    public static final String TAG = "MainActivity:LOG";
//ee
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grillebis);
        lv = findViewById(R.id.edittext);
        arrayList = new ArrayList<String>();




        Context context = getApplicationContext();
        CharSequence text = "Bonjour !!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.monmenu, menu);
        return true;
    }

}