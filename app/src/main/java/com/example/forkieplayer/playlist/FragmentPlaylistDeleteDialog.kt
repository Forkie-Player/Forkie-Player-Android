package com.example.forkieplayer.playlist

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.databinding.FragmentPlaylistDeleteDialogBinding

class FragmentPlaylistDeleteDialog(id: Long, position: Int) : DialogFragment() {

    lateinit var mainActivity: MainActivity
    val id = id
    val position = position

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlaylistDeleteDialogBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnDelete.setOnClickListener {
            (activity as MainActivity).deletePlaylist(id, position)
            CustomToast.makeText(mainActivity, "삭제되었습니다.")?.show()
            dismiss()
        }

        return binding.root
    }

    companion object {
        const val TAG = "FragmentPlaylistDeleteDialog"
        fun newInstance(id: Long, position: Int): FragmentPlaylistDeleteDialog{
            return FragmentPlaylistDeleteDialog(id, position)
        }
    }
}