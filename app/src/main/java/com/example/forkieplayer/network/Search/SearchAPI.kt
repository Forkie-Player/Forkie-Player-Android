package com.example.forkieplayer.network.Search

import com.example.forkieplayer.httpbody.SearchInfo
import retrofit2.Call

object SearchAPI {
    fun requestSearch(search: String): Call<ArrayList<SearchInfo>> {
        return SearchService.service.requestSearch(search)
    }
}