package com.example.snowtamdecoder;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public TextView longitude;
    public TextView latitude;
    public TextView aerodromeName;
    public TextView pays;
    public String longitudeText,latitudeText,aerodromeNameText,paysText;
    private GoogleMap mMap;
    public AerodromeInformation aerodromeInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getAerodromeInformation();
        setContentView(R.layout.activity_maps);
        longitude=this.findViewById(R.id.map_activity_longitude_text);
        latitude=this.findViewById(R.id.map_activity_latitude_text);
        aerodromeName=this.findViewById(R.id.map_activity_aerodrome_name_text);
        pays=this.findViewById(R.id.nom_pays_activity_text_value);
        longitude.setText(Double.toString(Global.aerodromeInfo.getLongitude()));
        latitude.setText(Double.toString(Global.aerodromeInfo.getLatitude()));
        aerodromeName.setText(Global.aerodromeInfo.getAirportName());
        pays.setText(Global.aerodromeInfo.getCountryName());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);

        RelativeLayout relativeLayout=this.findViewById(R.id.map_relative_layout);
        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(MapsActivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(MapActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Intent intent=new Intent(MapsActivity.this,SnowtamDecodeActivity.class);
                startActivity(intent);
                //Toast.makeText(MapsActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                //Intent intent=new Intent(MapsActivity.this,SnowtamDecodeActivity.class);
                //startActivity(intent);
                //Toast.makeText(MapActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                //Toast.makeText(MenuActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Global.aerodromeInfo.getLatitude(), Global.aerodromeInfo.getLongitude());

        mMap.addMarker(new MarkerOptions().position(sydney).title(Global.aerodromeInfo.getAirportName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void getAerodromeInformation(){

        GetDataSnotam serviceStream = RetrofitClientSnotam.getRetrofitInstance().create(GetDataSnotam.class);

        //System.out.println("begin sending request to server...");
        Call<List<AerodromeInformation>> call = serviceStream.getAerodromeInformation(Global.currentCode);

        call.enqueue(new Callback<List<AerodromeInformation>>() {
                         @Override
                         public void onResponse(Call<List<AerodromeInformation>> call, Response<List<AerodromeInformation>> response) {
                             aerodromeInformation=response.body().get(0);
                             System.out.println(aerodromeInformation);
                         }
                         @Override
                         public void onFailure(Call<List<AerodromeInformation>> call, Throwable t) {
                             Toast.makeText(MapsActivity.this, "error during requesting server geolocalisation ! verify first that the code is valid...", Toast.LENGTH_SHORT).show();
                         }
                     }
        );
        //return aerodromeInformation;
    }

}
