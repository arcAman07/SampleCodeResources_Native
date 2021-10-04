package com.example.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationListener locationListener;
    LocationManager locationManager;
    TextView latitude;
    TextView longitude;
    TextView accuracy;
    TextView altitude;
    TextView addressText;
    String editLatitude;
    String editLongitude;
    String editAccuracy;
    String editAltitude;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 ) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        latitude = (TextView) findViewById(R.id.editLatitude);
        longitude = (TextView) findViewById(R.id.editLongitude);
        accuracy = (TextView) findViewById(R.id.editAccuracy);
        altitude = (TextView) findViewById(R.id.editAltitude);
        addressText = (TextView) findViewById(R.id.editAddress);
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                editLatitude = String.valueOf(location.getLatitude());
                editLongitude = String.valueOf(location.getLongitude());
                editAccuracy = String.valueOf(location.getAccuracy());
                editAltitude = String.valueOf(location.getAltitude());
                try {
                    List<Address>addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    if (addressList!=null && addressList.size()>0) {
                        String address = "";
                        if (addressList.get(0).getThoroughfare()!=null) {
                            address += addressList.get(0).getThoroughfare()+" ";
                        }
                        if (addressList.get(0).getLocality()!=null) {
                            address += addressList.get(0).getLocality()+" ";
                        }
                        if (addressList.get(0).getPostalCode()!=null) {
                            address += addressList.get(0).getPostalCode()+" ";
                        }
                        if (addressList.get(0).getAdminArea()!=null) {
                            address += addressList.get(0).getAdminArea();
                        }
                        Log.i("info",address);
                        addressText.setText("Address: "+address);



                    }
                    latitude.setText("Latitude: "+editLatitude);
                    longitude.setText("Longitude: "+editLongitude);
                    accuracy.setText("Accuracy: "+editAccuracy);
                    altitude.setText("Altitude: "+editAltitude);

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };
        if (Build.VERSION.SDK_INT < 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            }
        }
    }
}