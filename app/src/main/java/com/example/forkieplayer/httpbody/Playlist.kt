package com.example.forkieplayer.httpbody

import com.google.gson.annotations.SerializedName

// 플레이리스트 조회 관련
data class GetPlaylistResponse (
    @SerializedName("data")
    val playlist: ArrayList<PlaylistInfo>
)

data class PlaylistInfo (
    val id: Int = 0,
    val thumbnail: String = "",
    val title: String = ""
)

// 플레이리스트 생성 관련

// 플레이리스트 삭제 관련

// 플레이리스트 제목 수정 관련