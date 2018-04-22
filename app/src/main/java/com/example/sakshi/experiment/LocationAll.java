package com.example.sakshi.experiment;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class LocationAll extends AppCompatActivity {

    double longitude, latitude;
    LocationManager locationManager;
    Location location;

    public void hospitalLocations() {
        if(!Utils.isNetworkAvailable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }
        else{
            updateLoc();
        }

    }

    public void updateLoc() {

        //Log.v("dfc","dcv");

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            throw new IllegalArgumentException("No GPS");
        } else if (!Utils.isGooglePlayServicesAvailable(this)) {
            throw new IllegalArgumentException("No Google Play Services Available");
        } else
            getLocation();


    }

    void getLocation() {

        if(checkLocationPermission()) {

            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);




            if (location != null) {
                Log.d("Achieved latitude=>", location.getLatitude() + ", longitide=> " + location.getLongitude());
            }

            if(location==null) {
                Log.d("GPS PRovider", "Enabled");
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }


            if (location == null)
                throw new IllegalArgumentException("Can't trace location");

            latitude = location.getLatitude();
            longitude = location.getLongitude();




        }
        else
            return;


    }

    public boolean checkLocationPermission()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

}
