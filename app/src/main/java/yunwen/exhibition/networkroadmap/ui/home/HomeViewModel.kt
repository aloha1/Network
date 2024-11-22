package yunwen.exhibition.networkroadmap.ui.home

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yunwen.exhibition.networkroadmap.data.ApiService
import yunwen.exhibition.networkroadmap.data.Constants.BASE_URL
import yunwen.exhibition.networkroadmap.data.NetData

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    val uiState: StateFlow<UiState> = _uiState

    private val retrofit = Retrofit
        .Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = apiService.getData()
                _text.postValue("")
                _uiState.value = UiState.Success(response)
            } catch (e: Exception) {
                _uiState.value =UiState.Error(e.message ?: UNKNOWN)
            }
        }
    }
    companion object{
        const val UNKNOWN = "Unknown Message"
    }

}

sealed class UiState {
    object Loading : UiState(),
    data class Success(val data: NetData) : UiState()
    data class Error(val msg: String) : UiState()
}