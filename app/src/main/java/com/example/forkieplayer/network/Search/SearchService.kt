package com.example.forkieplayer.network.Search

import com.example.forkieplayer.network.Forkie.IForkieService
import com.example.forkieplayer.sharedpreference.ForkieApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchService {

    private const val BASE_URL: String = "https://asia-northeast3-youtlist-db9d9.cloudfunctions.net//api/search/"

    private val interceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
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

    var service: ISearchService = retrofit.create(ISearchService::class.java)
}