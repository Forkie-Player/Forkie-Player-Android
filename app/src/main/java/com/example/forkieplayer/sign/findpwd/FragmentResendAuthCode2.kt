package com.example.forkieplayer.sign.findpwd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentResendAuthCode2Binding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentResendAuthCode2 : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentResendAuthCode2Binding.inflate(inflater, container, false)

        // TODO : 서버 연동 후 인증번호 재발송
        binding.btnOk.setOnClickListener {
            (activity as FindPwdAuthCodeActivity?)!!.setTimer()
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
        const val TAG = "FragmentResendAuthCode2"
        fun newInstance(): FragmentResendAuthCode2 {
            return FragmentResendAuthCode2()
        }
    }
}