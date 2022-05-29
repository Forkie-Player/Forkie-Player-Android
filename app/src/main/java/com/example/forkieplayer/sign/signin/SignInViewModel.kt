package com.example.forkieplayer.sign.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.SignInRequest
import com.example.forkieplayer.httpbody.SignInResponse
import com.example.forkieplayer.network.ForkieAPI
import com.example.forkieplayer.sharedpreference.NoToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel: ViewModel() {
    val signInOkCode: MutableLiveData<Boolean> = MutableLiveData()
    var accessToken: String = ""
    var refreshToken: String = ""

    fun requestSignIn(signInInfo: SignInRequest, mCallback: SignInActivity) {
        ForkieAPI.requestSignIn(signInInfo).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                accessToken = response.body()?.token?.accessToken ?: NoToken.NO_TOKEN.name
                refreshToken = response.body()?.token?.refreshToken ?: NoToken.NO_TOKEN.name

                if (response.isSuccessful) {
                    signInOkCode.postValue(true)
                    Log.d("[Forkie API] signIn", "로그인 요청 성공")
                } else {
                    mCallback.responseError(response.code())
                    Log.d("[Forkie API] signIn", "로그인 요청 실패, status code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                signInOkCode.postValue(false)
                Log.d("[Forkie API] signIn", "로그인 요청 실패 Throwable -> ${t.message}")
            }
        })
    }
}