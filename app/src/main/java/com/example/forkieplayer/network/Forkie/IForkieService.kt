package com.example.forkieplayer.network.Forkie

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
    fun requestCreatePlaylist(@Body playlistInfo: CreatePlaylistRequest): Call<CreatePlaylistResponse>

    // DELETE 메소드는 BODY를 지원하지 않아 HTTP 어노테이션으로 따로 처리해야 함
    @HTTP(method = "DELETE", path = "playlist", hasBody = true)
    fun requestDeletePlaylist(@Body playlistId: DeletePlaylistRequest): Call<Any>

    @PATCH("playlist")
    fun requestChangePlaylist(@Body changePlaylistInfo: ChangePlaylistRequest): Call<Any>

    @POST("play")
    fun requestAddVideo(@Body addVideoInfo: addVideoRequest): Call<Any>

    @GET("play")
    fun requestVideoInfo(@Path("playlistId") playlistId: Long): Call<GetVideoResponse>
}