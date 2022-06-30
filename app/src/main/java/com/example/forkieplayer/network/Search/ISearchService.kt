package com.example.forkieplayer.network.Search

import com.example.forkieplayer.httpbody.VideoInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchService {
    @GET("search/youtube")
    fun requestSearch(@Query("search") search: String): Call<ArrayList<VideoInfo>>

    @GET("popular")
    fun requestHitVideos(): Call<ArrayList<VideoInfo>>
}