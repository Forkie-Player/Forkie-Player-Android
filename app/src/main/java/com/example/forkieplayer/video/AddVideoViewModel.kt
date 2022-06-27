package com.example.forkieplayer.video

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.addVideoRequest
import com.example.forkieplayer.network.Forkie.ForkieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddVideoViewModel: ViewModel() {

    val addVideoOkCode: MutableLiveData<Boolean> = MutableLiveData()
    fun requestAddVideo(addVideoInfo: addVideoRequest) {
        ForkieAPI.requestAddVideo(addVideoInfo).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    addVideoOkCode.postValue(true)
                    Log.d("[Forkie API] addVideo", "영상 추가 요청 성공")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                addVideoOkCode.postValue(false)
                Log.d("[Forkie API] addVideo", "영상 추가 요청 성공")
            }
        })
    }
}