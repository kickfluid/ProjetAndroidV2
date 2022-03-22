package com.example.projetandroid;

import static com.example.projetandroid.DatabaseHelper.COL_2;
import static com.example.projetandroid.DatabaseHelper.COL_3;
import static com.example.projetandroid.DatabaseHelper.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telecom.Connection;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class Description extends AppCompat {
    public ArrayList<String> arrayList;
    DatabaseHelper myDb;
    ImageButton fav;
    int uniqueId = 0;
    TextView textId;
    TextView textedesc;
    Cursor res;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_description);
        ImageButton fav = (ImageButton) findViewById(R.id.favoris);
        textId = (TextView)findViewById(R.id.titredesc);
        textedesc= (TextView)findViewById(R.id.texteDEsc);
        SQLiteDatabase db = myDb.getReadableDatabase();
        LanguageManager lang=new LanguageManager(this);

        if (lang.getLang()=="fr") {
            res = myDb.affichageAP(textId.getText().toString());
            res.moveToFirst();
            textedesc.setText(res.getString(0));
        }
        if(lang.getLang()=="en"){
            res= myDb.affichageAPEng(textId.getText().toString());
            res.moveToFirst();
            textedesc.setText(res.getString(0));
        }


        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //modifier favoris
                if(uniqueId==0){
                    uniqueId=1;
                    boolean isUpdate =
                            myDb.updateData(textId.getText().toString(),uniqueId );
                    if(isUpdate == true)
                        Toast.makeText(Description.this,"Ajouté aux favoris",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Description.this,"Impossible de changer ce favoris",Toast.LENGTH_LONG).show();
                }else{
                    uniqueId=0;

                    boolean isUpdate =
                            myDb.updateData(textId.getText().toString(),uniqueId );
                    if(isUpdate == true)
                        Toast.makeText(Description.this,"Retiré des favoris",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Description.this,"Impossible de changer ce favoris",Toast.LENGTH_LONG).show();
                }
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

            case R.id.map:
                Intent mymap = new Intent(Description.this, MapsActivity.class);

                Description.this.startActivity(mymap);
                break;

            case R.id.lang:
                Intent mylang = new Intent(Description.this, Langues.class);

                Description.this.startActivity(mylang);
                break;
        }
        return true;
    }
}


