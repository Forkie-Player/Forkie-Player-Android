package com.example.forkieplayer.network.Search

import com.example.forkieplayer.httpbody.SearchInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchService {
    @GET("youtube")
    fun requestSearch(@Query("search") search: String): Call<ArrayList<SearchInfo>>
}