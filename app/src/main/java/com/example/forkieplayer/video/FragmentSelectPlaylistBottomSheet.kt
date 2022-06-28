package com.example.forkieplayer.video

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentSelectPlaylistBottomSheetBinding
import com.example.forkieplayer.httpbody.PlaylistInfo
import com.example.forkieplayer.playlist.PlaylistViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentSelectPlaylistBottomSheet : BottomSheetDialogFragment() {

    lateinit var playlistViewModel: PlaylistViewModel
    lateinit var videoActivity: VideoActivity
    lateinit var adapter: SelectPlaylistAdapter

    val datas = arrayListOf<PlaylistInfo>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        videoActivity = context as VideoActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSelectPlaylistBottomSheetBinding.inflate(inflater, container, false)

        playlistViewModel = ViewModelProvider(videoActivity).get(PlaylistViewModel::class.java)
        callGetPlaylistAPI()

        subscribeGetViewModel()

        binding.recyclerPlaylist.layoutManager = GridLayoutManager(videoActivity, 2)
        adapter = SelectPlaylistAdapter(datas, parentFragmentManager)
        binding.recyclerPlaylist.adapter = adapter

        return binding.root
    }

    // 모달 스타일 적용
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    companion object {
        const val TAG = "FragmentSelectPlaylistBottomSheet"
        fun newInstance(): FragmentSelectPlaylistBottomSheet {
            return FragmentSelectPlaylistBottomSheet()
        }
    }

    private fun callGetPlaylistAPI() = playlistViewModel.requestUserPlaylistInfo()

    private fun subscribeGetViewModel() {
        playlistViewModel.userPlaylistokCode.observe(this) {
            if (it) {
                val playlistData = playlistViewModel.playlistDataList

                if (playlistData.isNullOrEmpty()) {
                } else {
                    playlistData.forEach { i ->
                        if (i.thumbnail != null) {
                            datas.add(i)
                        } else {
                            datas.add(PlaylistInfo(i.id, "https://velog.velcdn.com/images/alsgk721/post/bb6d186b-5352-4db3-9a92-09d31cc81733/image.png", i.title, i.playCount))
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            } else {
                CustomToast.makeText(videoActivity, "죄송합니다. 플레이리스트 조회 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }
}