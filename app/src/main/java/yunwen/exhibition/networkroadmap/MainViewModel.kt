package yunwen.exhibition.networkroadmap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yunwen.exhibition.networkroadmap.Constants.BASE_URL
import yunwen.exhibition.networkroadmap.Constants.UNKNOWN_ERROR

class MainViewModel : ViewModel() {
    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    private val apiService = retrofit.create(ApiService::class.java)

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getData()
                _uiState.value = UiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: UNKNOWN_ERROR)
            }
        }
    }
}

sealed class UiState() {
    object Loading : UiState()
    data class Success(val data: NetData) : UiState()
    data class Error(val message: String) : UiState()
}