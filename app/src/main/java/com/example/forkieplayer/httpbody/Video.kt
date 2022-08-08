package com.example.forkieplayer.httpbody

import com.google.gson.annotations.SerializedName

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

data class GetVideoResponse(
    @SerializedName("data")
    val videolist: ArrayList<PlaylistVideoInfo>
)

data class PlaylistVideoInfo (
    val id: Long = 0,
    val title: String = "",
    val videoId: String = "",
    val sequence: Long = 0,
    val thumbnail: String = "",
    val start: Long = 0,
    val end: Long = 0,
    val channelImage: String = "",
    val channelTitle: String = "",
    val platform: String = ""
)