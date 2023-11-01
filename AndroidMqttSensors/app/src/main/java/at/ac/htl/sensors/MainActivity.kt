package at.ac.htl.sensors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
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
        setContent {
            AndroidMqttSensorsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationView(viewModel)
                }
            }
        }
    }
}

@Composable
fun LocationView(viewModel: LocationViewModel) {
    val model: State<Model> = viewModel.store.subscribeAsState(Model())
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "(${model.value.locationData.latitude}, ${model.value.locationData.longitude})"
        )
    }
}
@Preview(showBackground = true)
@Composable
fun LocationViewPreview() {
    val viewModel = LocationViewModel()
    AndroidMqttSensorsTheme {
        LocationView(viewModel)
    }
}
