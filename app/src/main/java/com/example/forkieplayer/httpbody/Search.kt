package com.example.forkieplayer.httpbody

data class SearchInfo(
    val videoId: String = "",
    val title: String = "",
    val thumbnail: String = "",
    val channelTitle: String = "",
    val channelImage: String = "",
    val duration: String = "",
    val views: String = "",
    val uploadedAt: String = "",
    val platform: String = ""
)