package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import com.google.android.gms.location.LocationRequest;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projetandroid.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    Button btLocation;



    SupportMapFragment supportMapFragment;
    private ActivityMapsBinding binding;

    Location currentLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_maps);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_map);
        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.location_map);
        mapFragment.getMapAsync(this);


/*
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/

        btLocation = findViewById(R.id.button_location);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(
                MapsActivity.this
        );

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check condition;
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                }else{
                    //When permission is not granted
                    //Request permission
                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1]
                == PackageManager.PERMISSION_GRANTED)) {

            getCurrentLocation();
        }else{
            Toast.makeText(getApplicationContext(),"Permission denied.",Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        //Initialize location manager
        LocationManager locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE
        );
        //Check condition
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            //When location service is enabled
            //Get last location
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    //Initialize location
                    Location location = task.getResult();
                    //Check condition
                    if (location != null){
                        //When location result is not null
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {
                                LatLng ici = new LatLng(location.getLatitude(), location.getLongitude());
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ici, 15));
                            }
                        });



                    } else {
                        //When location result is null
                        //Initialise location request
                        LocationRequest locationRequest = LocationRequest.create()
                                .setInterval(100)
                                .setFastestInterval(3000)
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setMaxWaitTime(100);

                        //Initialize location call back
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                //Initialize location

                                Location location1 = locationResult.getLastLocation();
                                LatLng ici = new LatLng(location1.getLatitude(), location1.getLongitude());


                            }
                        };
                        //Request location updates
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }

                }
            });
        }else {
            //When location service is not enabled
            //open location setting
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }



    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    /*
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude()+""+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.location_map);
                    mapFragment.getMapAsync(MapsActivity.this);

                     */
                }
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in Revel
        LatLng revel = new LatLng(43.45881182779149, 2.0043551872326795);
        googleMap.addMarker(new MarkerOptions().position(revel).title("Marché de Revel"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(revel, 15));

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
                Intent myIntent = new Intent(MapsActivity.this, Favoris.class);
                MapsActivity.this.startActivity(myIntent);
                break;

            case R.id.acc:
                Intent myacc = new Intent(MapsActivity.this, accueil.class);
                MapsActivity.this.startActivity(myacc);
                break;
        }


        return true;
    }
}

