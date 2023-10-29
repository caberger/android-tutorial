package at.ac.htl.sensors;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationManager {
    public static final String TAG = LocationManager.class
            .getSimpleName();
    private final FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;

    public LocationManager(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    private LocationRequest createRequest() {
        var locationRequest = new LocationRequest.Builder(5000)
                .build();
        return locationRequest;
    }

    public void start(Context context) {
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw new RuntimeException("Fatal: You did not request Location permissions in your code");
        }
        locationRequest = createRequest();
        fusedLocationClient.requestLocationUpdates(locationRequest, location -> {
            Log.i(TAG, String.format("location received %g, %g", location.getLatitude(), location.getLongitude()));
        }, context.getMainLooper());
    }
}
