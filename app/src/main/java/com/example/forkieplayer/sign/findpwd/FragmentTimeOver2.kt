package com.example.forkieplayer.sign.findpwd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentTimeOver2Binding
import com.example.forkieplayer.sign.signup.SignUpAuthCodeActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentTimeOver2  : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTimeOver2Binding.inflate(inflater, container, false)

        binding.btnResend.setOnClickListener {
            (activity as SignUpAuthCodeActivity?)!!.setTimer()
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
        const val TAG = "FragmentTimeOver2"
        fun newInstance(): FragmentTimeOver2 {
            return FragmentTimeOver2()
        }
    }
}