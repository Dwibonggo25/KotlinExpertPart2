package com.example.dicodingexpert2.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

fun getInterceptor(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    var okhttp = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    return okhttp
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(getInterceptor())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()


object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

//package com.example.dicodingexpert2.api
//
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//
//class NetworkConfig {
//
//    val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
//
//    fun getInterceptor(): OkHttpClient {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        var okhttp = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
//            .build()
//
//        return okhttp
//    }
//
//    fun getNetwork() : Retrofit{
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(getInterceptor())
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//    }
//
//    fun api() : ApiService{
//        return getNetwork().create(ApiService::class.java)
//    }
//}