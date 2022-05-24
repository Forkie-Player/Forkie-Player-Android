package com.example.forkieplayer.network

import com.example.forkieplayer.httpbody.*
import retrofit2.Call
import retrofit2.http.*

interface IForkieService {
    @POST("user/auth/signup/member")
    fun requestSignUp(@Body signUpInfo: SignUpRequest): Call<SignUpResponse>
}