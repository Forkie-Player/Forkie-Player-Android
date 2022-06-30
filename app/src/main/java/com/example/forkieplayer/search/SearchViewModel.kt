package com.example.forkieplayer.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.VideoInfo
import com.example.forkieplayer.network.Search.SearchAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    val _searchResultList = MutableLiveData<ArrayList<VideoInfo>>()
    val searchResultList: MutableLiveData<ArrayList<VideoInfo>>
        get() = _searchResultList

    fun requestSearch(search: String) {
        SearchAPI.requestSearch(search).enqueue(object: Callback<ArrayList<VideoInfo>> {
            override fun onResponse(call: Call<ArrayList<VideoInfo>>, response: Response<ArrayList<VideoInfo>>) {
                if (response.isSuccessful){
                    _searchResultList.postValue(response.body())
                    Log.d("[Forkie Search API] search", "영상 검색 성공")
                } else{
                    Log.d("[Forkie Search API] search", "영상 검색 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<VideoInfo>>, t: Throwable) {
                Log.d("[Forkie Search API] search", "영상 검색 실패 Throwable -> ${t.message}")
            }
        })
    }

    val _hitVideoList = MutableLiveData<ArrayList<VideoInfo>>()
    val hitVideoList: MutableLiveData<ArrayList<VideoInfo>>
        get() = _hitVideoList

    fun requestHitVideos() {
        SearchAPI.requestHitVideos().enqueue(object: Callback<ArrayList<VideoInfo>> {
            override fun onResponse(call: Call<ArrayList<VideoInfo>>, response: Response<ArrayList<VideoInfo>>) {
                if (response.isSuccessful){
                    _hitVideoList.postValue(response.body())
                    Log.d("[Forkie Search API] search - hot", "인기 동영상 조회 성공")
                } else{
                    Log.d("[Forkie Search API] search - hot", "인기 동영상 조회 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<VideoInfo>>, t: Throwable) {
                Log.d("[Forkie Search API] search - hot", "인기 동영상 조회 실패 Throwable -> ${t.message}")
            }
        })
    }
}