package com.example.forkieplayer.network.Forkie

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

    fun requestUserPlaylistInfo(): Call<GetPlaylistResponse> {
        return ForkieService.service.requestUserPlaylistInfo()
    }

    fun requestCreatePlaylist(@Body playlistInfo: CreatePlaylistRequest): Call<CreatePlaylistResponse> {
        return ForkieService.service.requestCreatePlaylist(playlistInfo)
    }

    fun requestDeletePlaylist(@Body playlistId: DeletePlaylistRequest): Call<Any> {
        return ForkieService.service.requestDeletePlaylist(playlistId)
    }

    fun requestChangePlaylist(@Body changePlaylistInfo: ChangePlaylistRequest): Call<Any> {
        return ForkieService.service.requestChangePlaylist(changePlaylistInfo)
    }

    fun requestAddVideo(@Body addVideoInfo: addVideoRequest): Call<Any> {
        return ForkieService.service.requestAddVideo(addVideoInfo)
    }
}