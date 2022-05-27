package com.example.forkieplayer.httpbody

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    val loginId: String = "",
    val password: String = "",
    val isPC: Boolean = false
)

data class SignInResponse(
    @SerializedName("data")
    val token: Tokens = Tokens()
)