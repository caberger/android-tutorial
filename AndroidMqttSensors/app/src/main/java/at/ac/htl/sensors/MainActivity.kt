package at.ac.htl.sensors

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.ac.htl.sensors.ui.theme.AndroidMqttSensorsTheme

class MainActivity : ComponentActivity() {
    var locationManager: LocationManager? = null;
    val TAG = "MainActivity"
    fun requestPermissions() {
        fun start() {
            Log.d(TAG, "permissions granted")
            val locationManager = LocationManager(this)
            locationManager.start(this)
            this.locationManager = locationManager
        }
        fun fail() {
            Log.e(TAG, "failed")
        }
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
                when {
                    permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        start()
                    }
                    permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                        start()
                    } else -> {
                        fail()
                    }
                }
        }
        locationPermissionRequest.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()
        setContent {
            AndroidMqttSensorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMqttSensorsTheme {
        Greeting("Android")
    }
}