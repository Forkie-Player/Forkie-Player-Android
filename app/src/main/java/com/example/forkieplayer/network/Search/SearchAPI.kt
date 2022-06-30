package com.example.forkieplayer.network.Search

import com.example.forkieplayer.httpbody.VideoInfo
import retrofit2.Call

object SearchAPI {
    fun requestSearch(search: String): Call<ArrayList<VideoInfo>> {
        return SearchService.service.requestSearch(search)
    }

    fun requestHitVideos(): Call<ArrayList<VideoInfo>> {
        return SearchService.service.requestHitVideos()
    }
}