package yunwen.exhibition.networkroadmap.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/character")
    suspend fun getData(@Query("page") page: Int = 1): NetData
}