package com.affinity.affinityteam.geoloc2;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;
    private String personalLocation;
    private float personalLat;
    private float personalLon;

    private float othersLat;
    private float othersLon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

        Button btnDistance = findViewById(R.id.btn_distance);
        btnDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_lat = findViewById(R.id.et_lat);
                EditText et_lon = findViewById(R.id.et_lon);
                TextView txt_dist = findViewById(R.id.txt_dist);

                othersLat = Float.parseFloat(et_lat.getText().toString());
                othersLon = Float.parseFloat(et_lon.getText().toString());

                Location loc1 = new Location("");
                loc1.setLatitude(personalLat);
                loc1.setLongitude(personalLon);

                Location loc2 = new Location("");
                loc2.setLatitude(othersLat);
                loc2.setLongitude(othersLon);

                float distanceInMeters = loc1.distanceTo(loc2)/1000;
                DecimalFormat df = new DecimalFormat("#.#");
                df.setRoundingMode(RoundingMode.CEILING);

                String distanceCalculated = df.format(distanceInMeters);//Float.toString(distanceInMeters);


                txt_dist.setText(distanceCalculated+" km");



            }
        });
        Button btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ActivityCompat.checkSelfPermission(MainActivity.this,ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    return;//Toast.makeText(getApplicationContext(),"All fine with permission!",Toast.LENGTH_LONG).show();
                }
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            TextView txtPos = findViewById(R.id.txt_pos);
                            //txtPos.setText(location.toString());
                            String lat="";
                            String lon="";
                            personalLocation = location.toString();
                            Pattern p1 = Pattern.compile("fused(.*?),");
                            Matcher m1 = p1.matcher(personalLocation);
                            while(m1.find()){
                                lat = m1.group(1);
                            }
                            p1 = Pattern.compile(",(.*?) hAcc=");
                            m1 = p1.matcher(personalLocation);
                            while(m1.find()){
                                lon = m1.group(1);
                            }
                            personalLat = Float.parseFloat(lat);
                            personalLon = Float.parseFloat(lon);

                            txtPos.setText(personalLat+","+personalLon);

                        } else {
                            Toast.makeText(getApplicationContext(),"Error location null",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }
}
