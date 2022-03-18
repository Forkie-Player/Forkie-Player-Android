package com.example.forkieplayer.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentPlaylistEditBottomSheetBinding
import com.example.forkieplayer.databinding.FragmentProfileMenuBottomSheetBinding
import com.example.forkieplayer.playlist.FragmentPlaylistDeleteDialog
import com.example.forkieplayer.playlist.FragmentPlaylistNameBottomSheet
import com.example.forkieplayer.playlist.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentProfileMenuBottomSheet : BottomSheetDialogFragment() {

    lateinit var profileActivity: ProfileActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileActivity = context as ProfileActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileMenuBottomSheetBinding.inflate(inflater, container, false)

        // TODO:password 클릭시 비밀번호 변경하는 fragment 뜨게 함
        binding.layoutPassword.setOnClickListener {
            Toast.makeText(profileActivity, "비밀번호 변경 is clicked!", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        // TODO:logout 클릭시 로그아웃하는 fragment 뜨게 함
        binding.layoutLogout.setOnClickListener {
            Toast.makeText(profileActivity, "로그아웃 is clicked!", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        // TODO:withdraw 클릭시 회원 탈퇴하는 fragment 뜨게 함
        binding.layoutWithdraw.setOnClickListener {
            Toast.makeText(profileActivity, "회원 탈퇴 is clicked!", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return binding.root
    }

    companion object {
        const val TAG = "FragmentProfileMenuBottomSheet"
        fun newInstance(): FragmentProfileMenuBottomSheet{
            return FragmentProfileMenuBottomSheet()
        }
    }
}