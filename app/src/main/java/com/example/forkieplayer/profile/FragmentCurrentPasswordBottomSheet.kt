package com.example.forkieplayer.profile

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentCurrentPasswordBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentCurrentPasswordBottomSheet : BottomSheetDialogFragment() {

    lateinit var profileActivity: ProfileActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileActivity = context as ProfileActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCurrentPasswordBottomSheetBinding.inflate(inflater, container, false)

        // 처음에는 버튼 비활성화
        binding.btnNext.isEnabled = false

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // edittext에 내용 입력되면 버튼 활성화
                val message = binding.etPassword.text.toString()
                binding.btnNext.isEnabled = message.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.btnNext.setOnClickListener {
            // TODO: 서버 연동 후 비밀번호 맞는지 확인하고 맞다면 아래 코드, 틀리면 메시지
            FragmentChangePasswordBottomSheet.newInstance().show(
                parentFragmentManager, FragmentChangePasswordBottomSheet.TAG
            )
            dismiss()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    companion object {
        const val TAG = "FragmentCurrentPasswordBottomSheet"
        fun newInstance(): FragmentCurrentPasswordBottomSheet{
            return FragmentCurrentPasswordBottomSheet()
        }
    }
}