package com.example.forkieplayer.play

data class PlayData (
    val id: Int,
    val title: String,
    val videoId: String,
    var sequence: Int,
    val thumbnail: Int,
    val start: Int,
    val end: Int,
    val channelImage: Int,
    val channelTitle: String
)