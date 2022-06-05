package com.example.forkieplayer.httpbody

import com.google.gson.annotations.SerializedName

// 플레이리스트 조회 관련
data class GetPlaylistResponse (
    @SerializedName("data")
    val playlist: ArrayList<PlaylistInfo>
)

data class PlaylistInfo (
    val id: Long = 0,
    val thumbnail: String = "",
    val title: String = ""
    //TODO: cnt 추가
)

// 플레이리스트 생성 관련
data class CreatePlaylistRequest (
    val title: String = ""
)

data class CreatePlaylistResponse (
    @SerializedName("data")
    val newPlaylist: NewPlaylist
)

data class NewPlaylist (
    val id: Long = 0,
    val title: String = ""
)

// 플레이리스트 삭제 관련
data class DeletePlaylistRequest (
    val playlistId: Long = 0
)

// 플레이리스트 제목 수정 관련
data class ChangePlaylistRequest (
    val playlistId: Long = 0,
    val title: String = ""
)