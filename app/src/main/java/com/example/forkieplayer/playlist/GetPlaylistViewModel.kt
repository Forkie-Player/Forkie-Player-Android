package com.example.forkieplayer.playlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.GetPlaylistResponse
import com.example.forkieplayer.httpbody.PlaylistInfo
import com.example.forkieplayer.network.ForkieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPlaylistViewModel: ViewModel() {
    val okCode: MutableLiveData<Boolean> = MutableLiveData()

    private var _playlistDataList: ArrayList<PlaylistInfo>? = arrayListOf()
    val playlistDataList: ArrayList<PlaylistInfo>?
        get() = _playlistDataList

    fun requestUserPlaylistInfo() {
        ForkieAPI.requestUserPlaylistInfo().enqueue(object: Callback<GetPlaylistResponse> {
            override fun onResponse(call: Call<GetPlaylistResponse>, response: Response<GetPlaylistResponse>) {
                _playlistDataList = response.body()?.playlist

                if (response.isSuccessful){
                    okCode.value = true
                    Log.d("[Forkie API] getPlaylist", "플레이리스트 조회 성공")
                } else{
                    okCode.value = false
                    Log.d("[Forkie API] getPlaylist", "플레이리스트 조회 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<GetPlaylistResponse>, t: Throwable) {
                okCode.value = false
                Log.d("[Forkie API] getPlaylist", "플레이리스트 조회 실패 Throwable -> ${t.message}")
            }
        })
    }
}