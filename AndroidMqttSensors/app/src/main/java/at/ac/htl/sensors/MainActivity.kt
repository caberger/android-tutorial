package at.ac.htl.sensors

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.ac.htl.sensors.model.LocationViewModel
import at.ac.htl.sensors.model.Model
import at.ac.htl.sensors.ui.theme.AndroidMqttSensorsTheme


class MainActivity : ComponentActivity() {
    var TAG = MainActivity::class.java.simpleName
    private var locationManager = LocationManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager.requestPermissions(this)
        val viewModel: LocationViewModel by viewModels()
        viewModel.store.subscribe( {
            Log.d(TAG, "loc:" + it.locationData.latitude)
        })
        setContent {
            AndroidMqttSensorsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    locationView(viewModel)
                }
            }
        }
    }
}

@Composable
fun locationView(viewModel: LocationViewModel) {
    var model: State<Model> = viewModel.store.subscribeAsState(Model())
    Text(
        text = "Hello (${model.value.locationData.latitude}, ${model.value.locationData.longitude})!"
    )
}
@Preview(showBackground = true)
@Composable
fun locationViewPreview() {
    var viewModel = LocationViewModel()
    AndroidMqttSensorsTheme {
        locationView(viewModel)
    }
}
