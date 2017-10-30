package voca.xvocaandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


public class MyLocationService extends Service implements LocationListener {
    Location currentLocation;
    LocationManager lm;
    MyLocationBinder binder;

    final String TAG = "MyLocationService";

    public MyLocationService() {
        binder = new MyLocationBinder();
        currentLocation = new Location("INITIAL");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startUpdating();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        lm.removeUpdates(this);

        return super.onUnbind(intent);
    }

    public void startUpdating() {
        Log.d(TAG, "startUpdating: ");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    // START LocationListener
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");
        this.currentLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
        switch (provider) {
            case LocationManager.GPS_PROVIDER:
                startUpdating();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
    // END LocationListener

    class MyLocationBinder extends Binder {
        MyLocationService getService() {
            Log.d(TAG, "getService: ");
            return MyLocationService.this;
        }
    }
}
