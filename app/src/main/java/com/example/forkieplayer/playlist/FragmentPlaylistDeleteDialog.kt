package com.example.forkieplayer.playlist

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentPlaylistDeleteDialogBinding

class FragmentPlaylistDeleteDialog : DialogFragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlaylistDeleteDialogBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnDelete.setOnClickListener {
            //TODO:서버에 삭제 정보 보내기
            CustomToast.makeText(mainActivity, "deleted!")?.show()
            dismiss()
        }

        binding.btnCancel.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    binding.btnCancel.setTextColor(ContextCompat.getColor(requireContext(), R.color.forkie_b1))
                }
                MotionEvent.ACTION_UP -> {
                    binding.btnCancel.setTextColor(ContextCompat.getColor(requireContext(), R.color.forkie_g4))
                }
            }
            false
        }

        binding.btnDelete.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    binding.btnDelete.setTextColor(ContextCompat.getColor(requireContext(), R.color.forkie_b1))
                }
                MotionEvent.ACTION_UP -> {
                    binding.btnDelete.setTextColor(ContextCompat.getColor(requireContext(), R.color.forkie_g4))
                }
            }
            false
        }

        return binding.root
    }

    companion object {
        const val TAG = "FragmentPlaylistDeleteDialog"
        fun newInstance(): FragmentPlaylistDeleteDialog{
            return FragmentPlaylistDeleteDialog()
        }
    }
}