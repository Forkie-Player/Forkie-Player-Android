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
import com.example.forkieplayer.databinding.FragmentChangePasswordBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.regex.Pattern


class FragmentChangePasswordBottomSheet : BottomSheetDialogFragment() {

    lateinit var profileActivity: ProfileActivity
    lateinit var binding: FragmentChangePasswordBottomSheetBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileActivity = context as ProfileActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChangePasswordBottomSheetBinding.inflate(inflater, container, false)

        // 처음에는 버튼 비활성화
        binding.btnNext.isEnabled = false

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // edittext에 내용 입력되면 버튼 활성화
                val message = binding.etPassword.text.toString()
                binding.btnNext.isEnabled = message.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable) {
                val etPwd = binding.etPassword.text
                if (etPwd != null) {
                    if (etPwd.length < 8 || etPwd.length > 20) {
                        binding.textInputLayout.error = "8자 이상, 20자 이하로 입력해야해요."
                        binding.btnNext.isEnabled = false
                    } else {
                        if (checkPwdRule()) {
                            binding.textInputLayout.error = null
                            binding.btnNext.isEnabled = true
                        } else {
                            binding.textInputLayout.error = "알파벳, 숫자, 특수문자가 들어가야해요."
                            binding.btnNext.isEnabled = false
                        }
                    }
                }
            }
        })

        binding.btnNext.setOnClickListener {
            FragmentCheckPasswordBottomSheet.newInstance().show(
                parentFragmentManager, FragmentCheckPasswordBottomSheet.TAG
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
        const val TAG = "FragmentChangePasswordBottomSheet"
        fun newInstance(): FragmentChangePasswordBottomSheet{
            return FragmentChangePasswordBottomSheet()
        }
    }

    private fun checkPwdRule(): Boolean {
        val rule = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@!%~*#?&^?_=+\$]).{8,20}.\$"
        val pattern = Pattern.compile(rule)

        return pattern.matcher(binding.etPassword.text.toString()).find()
    }
}