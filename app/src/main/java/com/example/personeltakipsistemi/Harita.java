package com.example.personeltakipsistemi;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Harita extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public String konum="";
    public Double lon;
    public Double lat;
    public String personel_adi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harita);
        konum=getIntent().getExtras().getString("veri");
        String parcala[]=konum.split(",");
           lat=Double.valueOf(parcala[0]);
          lon=Double.valueOf(parcala[1]);
          personel_adi=String.valueOf(parcala[2]);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng konum = new LatLng(lon, lat);
        mMap.addMarker(new MarkerOptions().position(konum).title(String.valueOf(personel_adi)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(konum));
    }
}
