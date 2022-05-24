package com.example.forkieplayer.httpbody

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val loginId: String = "",
    val password: String = "",
    val isPC: Boolean = false
)

data class SignUpResponse(
    @SerializedName("data")
    val token: Tokens = Tokens()
)

data class Tokens (
    val accessToken: String = "",
    val refreshToken: String = ""
)