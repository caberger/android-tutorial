package at.ac.htl.sensors;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.Map;

import at.ac.htl.sensors.model.LocationViewModel;
import at.ac.htl.sensors.model.Model;
import io.reactivex.rxjava3.disposables.Disposable;

public class LocationManager {
    public static final String TAG = LocationManager.class
            .getSimpleName();
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private Disposable locationServiceStateSubscription;

    public void start(ComponentActivity activity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ActivityCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw new RuntimeException("Fatal: You did not request Location permissions in your code");
        }
        locationRequest = new LocationRequest.Builder(2000)
                .build();

        final var viewModel = new ViewModelProvider(activity).get(LocationViewModel.class);

        fusedLocationClient.requestLocationUpdates(locationRequest, loc -> {
            viewModel.next(model -> model.locationData = new Model.LocationData(loc.getLatitude(), loc.getLongitude(), true));
        }, activity.getMainLooper());
        Log.i(TAG, "Location Requests started");
    }
    public void requestPermissions(ComponentActivity activity) {
        activity
            .registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                    result -> evaluateRequestedPermissionsResult(activity, result))
            .launch(new String[] {
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION
            });
        final var available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity) == ConnectionResult.SUCCESS;
        Log.i(TAG, "Play services available: " + (available ? "true" : "false"));
    }
    private void subscribeToLocationServiceStateChanges(ComponentActivity activity) {
        final var viewModel = new ViewModelProvider(activity).get(LocationViewModel.class);
        locationServiceStateSubscription = viewModel.getStore().subscribe(model -> {
            if (!model.locationServicesStarted) {
                if (model.permissions.coarse() || model.permissions.fine()) {
                    viewModel.next(mdl -> mdl.locationServicesStarted = true);
                    activity.runOnUiThread(() -> start(activity));
                }
            }
        });
    }
    private void evaluateRequestedPermissionsResult(ComponentActivity activity, Map<String, Boolean> result) {
        subscribeToLocationServiceStateChanges(activity);
        final var coarseAllowed = Boolean.TRUE.equals(result.getOrDefault(ACCESS_COARSE_LOCATION, false));
        final var fineAllowed = Boolean.TRUE.equals(result.getOrDefault(ACCESS_FINE_LOCATION, false));
        final var viewModel = new ViewModelProvider(activity).get(LocationViewModel.class);
        viewModel.next(model -> {
            model.permissions = new Model.LocationPermissions(fineAllowed, coarseAllowed);
        });
    }
}
