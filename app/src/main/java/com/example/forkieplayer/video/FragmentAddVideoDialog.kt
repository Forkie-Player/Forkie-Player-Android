package com.example.forkieplayer.video

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.databinding.FragmentAddVideoDialogBinding
import com.example.forkieplayer.playlist.MainActivity

class FragmentAddVideoDialog(id: Long, title: String) : DialogFragment() {

    lateinit var videoActivity: VideoActivity
    val id = id
    val title = title

    override fun onAttach(context: Context) {
        super.onAttach(context)
        videoActivity = context as VideoActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAddVideoDialogBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.tvPlaylist.text = title

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnAdd.setOnClickListener {
            videoActivity.callAddVideoAPI(id)
            dismiss()
        }

        return binding.root
    }

    companion object {
        const val TAG = "FragmentAddVideoDialog"
        fun newInstance(id: Long, title: String): FragmentAddVideoDialog{
            return FragmentAddVideoDialog(id, title)
        }
    }
}