package com.example.forkieplayer.play

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.GetVideoResponse
import com.example.forkieplayer.httpbody.PlaylistVideoInfo
import com.example.forkieplayer.network.Forkie.ForkieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetVideoViewModel: ViewModel() {
    val getVideoOkCode: MutableLiveData<Boolean> = MutableLiveData()

    private var _videoDataList: ArrayList<PlaylistVideoInfo>? = arrayListOf()
    val videoDataList: ArrayList<PlaylistVideoInfo>?
        get() = _videoDataList

    // 플레이리스트 조회
    fun requestVideoInfo(playlistId: Long) {
        ForkieAPI.requestVideoInfo(playlistId).enqueue(object: Callback<GetVideoResponse> {
            override fun onResponse(call: Call<GetVideoResponse>, response: Response<GetVideoResponse>) {
                _videoDataList = response.body()?.videolist

                if (response.isSuccessful){
                    getVideoOkCode.value = true
                    Log.d("[Forkie API] getVideo", "비디오 조회 성공, $videoDataList")
                } else{
                    getVideoOkCode.value = false
                    Log.d("[Forkie API] getVideo", "비디오 조회 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<GetVideoResponse>, t: Throwable) {
                getVideoOkCode.value = false
                Log.d("[Forkie API] getPlaylist", "비디오 조회 실패 Throwable -> ${t.message}")
            }
        })
    }
}