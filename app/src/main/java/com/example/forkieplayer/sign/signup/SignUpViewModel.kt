package com.example.forkieplayer.sign.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forkieplayer.httpbody.SignUpRequest
import com.example.forkieplayer.httpbody.SignUpResponse
import com.example.forkieplayer.network.ForkieAPI
import com.example.forkieplayer.sharedpreference.NoToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel: ViewModel() {
    val signupOkCode: MutableLiveData<Boolean> = MutableLiveData()
    var accessToken: String = ""
    var refreshToken: String = ""

    fun requestSignup(signUpInfo: SignUpRequest) {
        ForkieAPI.requestSignUp(signUpInfo).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                signupOkCode.postValue(true)
                accessToken = response.body()?.token?.accessToken ?: NoToken.No_Token.name
                refreshToken = response.body()?.token?.refreshToken ?: NoToken.No_Token.name
                Log.d("[Forkie API] signUp", "회원가입 요청 성공")
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                signupOkCode.postValue(false)
                Log.d("[Forkie API] signUp", "회원가입 요청 실패 Throwable -> ${t.message}")
            }
        })
    }
}