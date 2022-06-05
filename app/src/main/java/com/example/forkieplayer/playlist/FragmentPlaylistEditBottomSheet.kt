package com.example.forkieplayer.playlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentPlaylistEditBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentPlaylistEditBottomSheet(id: Long, position: Int) : BottomSheetDialogFragment() {

    lateinit var mainActivity: MainActivity
    val id = id
    val position = position

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlaylistEditBottomSheetBinding.inflate(inflater, container, false)

        // change 클릭시 제목 변경하는 fragment 뜨게 함
        binding.layoutChange.setOnClickListener {
            FragmentPlaylistNameBottomSheet.newInstance(id, position).show(
                parentFragmentManager, FragmentPlaylistNameBottomSheet.TAG
            )
            dismiss()
        }

        // delete 클릭시 플레이리스트 삭제하는 fragment 뜨게 함
        binding.layoutDelete.setOnClickListener {
            FragmentPlaylistDeleteDialog.newInstance(id, position).show(
                parentFragmentManager, FragmentPlaylistDeleteDialog.TAG
            )
            dismiss()
        }

        return binding.root
    }

    // 모달 스타일 적용
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    companion object {
        const val TAG = "FragmentPlaylistEditBottomSheet"
        fun newInstance(id: Long, position: Int): FragmentPlaylistEditBottomSheet{
            return FragmentPlaylistEditBottomSheet(id, position)
        }
    }
}