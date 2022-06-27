package com.example.forkieplayer.httpbody

data class addVideoRequest(
    val playlistId: Long = 0,
    val thumbnail: String = "",
    val startTime: Long = 0,
    val endTime: Long = 0,
    val videoId: String = "",
    val title: String = "",
    val channelTitle: String = "",
    val channelImg: String = "",
    val platform: String = "YOUTUBE"
)