package com.example.forkieplayer.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.SearchInfo
import com.example.forkieplayer.network.Search.SearchAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    val _searchResultList = MutableLiveData<ArrayList<SearchInfo>>()
    val searchResultList: MutableLiveData<ArrayList<SearchInfo>>
        get() = _searchResultList

    fun requestSearch(search: String) {
        SearchAPI.requestSearch(search).enqueue(object: Callback<ArrayList<SearchInfo>> {
            override fun onResponse(call: Call<ArrayList<SearchInfo>>, response: Response<ArrayList<SearchInfo>>) {
                if (response.isSuccessful){
                    _searchResultList.postValue(response.body())
                    Log.d("[Forkie Search API] search", "영상 검색 성공")
                } else{
                    Log.d("[Forkie Search API] search", "영상 검색 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<SearchInfo>>, t: Throwable) {
                Log.d("[Forkie Search API] search", "영상 검색 실패 Throwable -> ${t.message}")
            }
        })
    }
}