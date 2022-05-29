package com.example.forkieplayer.network

import com.example.forkieplayer.sharedpreference.ForkieApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ForkieService {

    private const val BASE_URL: String = "https://forkie-api.com/api/"

    private val interceptor = Interceptor { chain ->
        val accessToken = ForkieApplication.prefs.accessToken
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var service: IForkieService = retrofit.create(IForkieService::class.java)
}