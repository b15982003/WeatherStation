package com.example.weatherstation.network

import com.example.weatherstation.data.WeatherData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL = "https://data.epa.gov.tw"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface RetrofitApiService {

    @GET("/api/v1/aqx_p_432")
    suspend fun getWeatherStatus(
        @Query("limit") limit : Int = 1000,
        @Query("api_key") api_key : String = "9be7b239-557b-4c10-9775-78cadfc555e9",
        @Query("format") format : String = "json"
    ): Response<WeatherData>

}

object RetrofitApi {
    val retrofitService: RetrofitApiService by lazy { retrofit.create(
        RetrofitApiService::class.java) }
}
