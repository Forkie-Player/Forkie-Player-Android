package com.example.forkieplayer.network

import com.example.forkieplayer.httpbody.*
import retrofit2.Call
import retrofit2.http.*

object ForkieAPI {
    fun requestSignUp(@Body signUpInfo: SignUpRequest): Call<SignUpResponse> {
        return ForkieService.service.requestSignUp(signUpInfo)
    }

    fun requestSignIn(@Body signInInfo: SignInRequest): Call<SignInResponse> {
        return ForkieService.service.requestSignIn(signInInfo)
    }
}