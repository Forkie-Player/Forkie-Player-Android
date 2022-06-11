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
    val searchokCode: MutableLiveData<Boolean> = MutableLiveData()

    private var _searchResultList: ArrayList<SearchInfo>? = arrayListOf()
    val searchResultList: ArrayList<SearchInfo>?
        get() = _searchResultList

    fun requestSearch(search: String) {
        SearchAPI.requestSearch(search).enqueue(object: Callback<ArrayList<SearchInfo>> {
            override fun onResponse(call: Call<ArrayList<SearchInfo>>, response: Response<ArrayList<SearchInfo>>) {
                _searchResultList = response.body()

                if (response.isSuccessful){
                    searchokCode.value = true
                    Log.d("[Forkie Search API] search", "영상 검색 성공")
                } else{
                    searchokCode.value = false
                    Log.d("[Forkie Search API] search", "영상 검색 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<SearchInfo>>, t: Throwable) {
                searchokCode.value = false
                Log.d("[Forkie Search API] search", "영상 검색 실패 Throwable -> ${t.message}")
            }
        })
    }
}