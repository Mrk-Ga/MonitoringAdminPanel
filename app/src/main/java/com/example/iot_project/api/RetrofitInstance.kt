package com.example.iot_project.api

import com.example.iot_project.BuildConfig
//import com.example.iot_project.api.RetrofitInstance.api
import com.example.iot_project.api.interfaces.AccessLogApi
import com.example.iot_project.api.interfaces.BuildingApi
import com.example.iot_project.api.interfaces.ClientApi
import com.example.iot_project.api.interfaces.GateApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL:String  = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun createGateApi(baseUrl: String): GateApi =
        createRetrofit(baseUrl).create(GateApi::class.java)

    fun createClientApi(baseUrl: String): ClientApi =
        createRetrofit(baseUrl).create(ClientApi::class.java)

    fun createBuildingApi(baseUrl: String): BuildingApi =
        createRetrofit(baseUrl).create(BuildingApi::class.java)

    fun createAccessLogApi(baseUrl: String): AccessLogApi =
        createRetrofit(baseUrl).create(AccessLogApi::class.java)
}