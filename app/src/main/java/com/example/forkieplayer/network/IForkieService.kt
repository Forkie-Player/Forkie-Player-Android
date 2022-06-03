package com.example.forkieplayer.network

import com.example.forkieplayer.httpbody.*
import retrofit2.Call
import retrofit2.http.*

interface IForkieService {
    @POST("user/auth/signup/member")
    fun requestSignUp(@Body signUpInfo: SignUpRequest): Call<SignUpResponse>

    @POST("user/auth/login/member")
    fun requestSignIn(@Body signInInfo: SignInRequest): Call<SignInResponse>

    @GET("playlist")
    fun requestUserPlaylistInfo(): Call<GetPlaylistResponse>

    @POST("playlist")
    fun requestCreatePlaylist(@Body playlistInfo: CreatePlaylistRequest): Call<Any>
}