package com.example.forkieplayer.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentProfileMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentProfileMenuBottomSheet : BottomSheetDialogFragment() {

    lateinit var profileActivity: ProfileActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileActivity = context as ProfileActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.layoutPassword.setOnClickListener {
            FragmentCurrentPasswordBottomSheet.newInstance().show(
                parentFragmentManager, FragmentCurrentPasswordBottomSheet.TAG
            )
            dismiss()
        }

        // TODO:logout 클릭시 로그아웃하는 fragment 뜨게 함
        binding.layoutLogout.setOnClickListener {
            CustomToast.makeText(profileActivity, "로그아웃 is clicked!")?.show()
            dismiss()
        }

        // TODO:withdraw 클릭시 회원 탈퇴하는 fragment 뜨게 함
        binding.layoutWithdraw.setOnClickListener {
            CustomToast.makeText(profileActivity, "회원 탈퇴 is clicked!")?.show()
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
        const val TAG = "FragmentProfileMenuBottomSheet"
        fun newInstance(): FragmentProfileMenuBottomSheet{
            return FragmentProfileMenuBottomSheet()
        }
    }
}