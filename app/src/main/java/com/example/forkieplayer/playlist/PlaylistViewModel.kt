package com.example.forkieplayer.playlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.CreatePlaylistRequest
import com.example.forkieplayer.httpbody.GetPlaylistResponse
import com.example.forkieplayer.httpbody.PlaylistInfo
import com.example.forkieplayer.network.ForkieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel: ViewModel() {
    val userPlaylistokCode: MutableLiveData<Boolean> = MutableLiveData()

    private var _playlistDataList: ArrayList<PlaylistInfo>? = arrayListOf()
    val playlistDataList: ArrayList<PlaylistInfo>?
        get() = _playlistDataList

    // 플레이리스트 조회
    fun requestUserPlaylistInfo() {
        ForkieAPI.requestUserPlaylistInfo().enqueue(object: Callback<GetPlaylistResponse> {
            override fun onResponse(call: Call<GetPlaylistResponse>, response: Response<GetPlaylistResponse>) {
                _playlistDataList = response.body()?.playlist

                if (response.isSuccessful){
                    userPlaylistokCode.value = true
                    Log.d("[Forkie API] getPlaylist", "플레이리스트 조회 성공")
                } else{
                    userPlaylistokCode.value = false
                    Log.d("[Forkie API] getPlaylist", "플레이리스트 조회 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<GetPlaylistResponse>, t: Throwable) {
                userPlaylistokCode.value = false
                Log.d("[Forkie API] getPlaylist", "플레이리스트 조회 실패 Throwable -> ${t.message}")
            }
        })
    }

    // 플레이리스트 추가
    val addPlaylistOkCode: MutableLiveData<Boolean> = MutableLiveData()

    fun requestCreatePlaylist(playlistInfo: CreatePlaylistRequest) {
        ForkieAPI.requestCreatePlaylist(playlistInfo).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    addPlaylistOkCode.postValue(true)
                    Log.d("[Forkie API] createPlaylist", "플레이리스트 생성 요청 성공")
                } else {
                    Log.d("[Forkie API] createPlaylist", "플레이리스트 생성 요청 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                addPlaylistOkCode.postValue(false)
                Log.d("[Forkie API] createPlaylist", "플레이리스트 생성 요청 실패 Throwable -> ${t.message}")
            }
        })
    }
}