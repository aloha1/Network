package yunwen.exhibition.networkroadmap

import retrofit2.http.GET

interface ApiService {
    @GET("api/character")
    suspend fun getData(): NetData
}