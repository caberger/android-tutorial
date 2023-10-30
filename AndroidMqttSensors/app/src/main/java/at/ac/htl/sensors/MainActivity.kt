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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import at.ac.htl.sensors.model.LocationViewModel
import at.ac.htl.sensors.model.Model
import at.ac.htl.sensors.ui.theme.AndroidMqttSensorsTheme


class MainActivity : ComponentActivity() {
    var TAG = MainActivity::class.java.simpleName
    private var locationManager = LocationManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager.requestPermissions(this)
        //val viewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        val viewModel: LocationViewModel by viewModels()
        viewModel.data.observe(this, {
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
    //val itemState by viewModel.
    var model = viewModel.data.observeAsState()
    Text(
        text = "Hello ${model.value?.locationData?.latitude}!"
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
