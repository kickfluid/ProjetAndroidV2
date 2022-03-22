package com.example.projetandroid;

import static com.example.projetandroid.DatabaseHelper.COL_2;
import static com.example.projetandroid.DatabaseHelper.COL_4;
import static com.example.projetandroid.DatabaseHelper.TABLE_NAME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Entity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Favoris extends AppCompatActivity {


    private SearchView searchView;
    private SQLiteDatabase dataBase;

    //variables to hold staff records
    private ArrayList<String> stafid = new ArrayList<String>();

    private ListView userList;
    private AlertDialog.Builder build;
    DatabaseHelper myDb;

    public static final String TAG = "MainActivity:LOG";
//ee
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grillebis);

        myDb = new DatabaseHelper(this);
        userList = (ListView) findViewById(R.id.edittext);
        TextView text1 = (TextView) findViewById(R.id.txtstafid);

        DatabaseHelper handler = new DatabaseHelper(this);
        // Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor todoCursor = db.rawQuery("select rowid _id, " + COL_2 + " from " +TABLE_NAME +" where  "+ COL_4 + " = 1",null);

        ListView lvItems = (ListView) findViewById(R.id.edittext);
// Setup cursor adapter using cursor from last step
        AdapterP todoAdapter = new AdapterP(this, todoCursor);
// Attach cursor adapter to the ListView
        lvItems.setAdapter(todoAdapter);

        todoAdapter.changeCursor(todoCursor);
        todoAdapter.notifyDataSetChanged();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item1) {
        switch (item1.getItemId()) {
            case R.id.map:
                Intent myIntent = new Intent(Favoris.this, MapsActivity.class);

                Favoris.this.startActivity(myIntent);
                break;

            case R.id.acc:
                Intent myacc = new Intent(Favoris.this, accueil.class);
                Favoris.this.startActivity(myacc);
                break;

            case R.id.lang:
                Intent mylang = new Intent(Favoris.this, Langues.class);

                Favoris.this.startActivity(mylang);
                break;
        }

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.monmenu, menu);
        return true;
    }





}