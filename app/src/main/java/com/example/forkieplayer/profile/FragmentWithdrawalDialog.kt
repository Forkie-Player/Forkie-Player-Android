package com.example.forkieplayer.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentWithdrawalDialogBinding
import com.example.forkieplayer.sign.IntroActivity

class FragmentWithdrawalDialog : DialogFragment() {

    lateinit var profileActivity: ProfileActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileActivity = context as ProfileActivity
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWithdrawalDialogBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnWithdrawal.setOnClickListener {
            //TODO:서버에 탈퇴 정보 보내기
            CustomToast.makeText(profileActivity, "탈퇴되었습니다.")?.show()
            dismiss()
            ActivityCompat.finishAffinity(profileActivity)
            val intent = Intent(profileActivity, IntroActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {
        const val TAG = "FragmentWithdrawalDialog"
        fun newInstance(): FragmentWithdrawalDialog{
            return FragmentWithdrawalDialog()
        }
    }
}