package yunwen.exhibition.networkroadmap

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import yunwen.exhibition.networkroadmap.MainActivity.Companion.TAG
import yunwen.exhibition.networkroadmap.ui.theme.NetWorkRoadMapTheme

class MainActivity : ComponentActivity() {
    companion object{
        const val TAG = "MyTag"
    }
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContent {
            NetWorkRoadMapTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MainViewModel::class]
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var data: NetData? by remember { mutableStateOf(null) }
    when (uiState) {
        is UiState.Loading -> {
            Log.d(TAG, "Loading")
        }
        is UiState.Success -> {
            data = (uiState as UiState.Success).data
            Log.d(TAG, "success: $data")

        }

        is UiState.Error -> {
            Log.d(TAG, "error:" + (uiState as UiState.Error).message)
        }
    }
    LaunchedEffect(key1 = Unit){
        viewModel.fetchData()
    }
    data?.let {
        ItemsScreen(it)
    }

}

@Composable
fun ItemsScreen(data: NetData) {
    LazyColumn {
        items(
            items = data.results,
            itemContent = { item ->
                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = item.status,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = item.species,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetWorkRoadMapTheme {
    }
}