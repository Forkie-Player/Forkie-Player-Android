package com.example.forkieplayer.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentResendAuthCodeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentResendAuthCode : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentResendAuthCodeBinding.inflate(inflater, container, false)

        // TODO : 서버 연동 후 인증번호 재발송
        binding.btnOk.setOnClickListener {
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
        const val TAG = "FragmentResendAuthCode"
        fun newInstance(): FragmentResendAuthCode {
            return FragmentResendAuthCode()
        }
    }
}