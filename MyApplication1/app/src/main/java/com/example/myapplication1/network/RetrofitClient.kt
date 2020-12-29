package com.example.myapplication1.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {


   private fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

   private fun provideApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mocki.io/v1/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .serializeNulls()
                        .setLenient()
                        .create()
                )
            ).client(provideOkHttpClient())
            .build()
    }

    fun <T> buildService(serviceClass: Class<T>): T = provideApiClient().create(serviceClass)
}