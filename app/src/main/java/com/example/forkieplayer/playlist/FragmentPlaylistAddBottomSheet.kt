package com.example.forkieplayer.playlist

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentPlaylistAddBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FragmentPlaylistAddBottomSheet : BottomSheetDialogFragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlaylistAddBottomSheetBinding.inflate(inflater, container, false)

        // edittext에 내용 입력시 버튼 활성화
        binding.btnCreate.isEnabled = false

        binding.etPlaylistTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val message = binding.etPlaylistTitle.text.toString()
                binding.btnCreate.isEnabled = message.isNotEmpty()

                if (message.length == 12) {
                    Toast.makeText(mainActivity, "제목은 12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.etPlaylistTitle.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(12))

        // 입력 내용 서버에 보내기
        binding.btnCreate.setOnClickListener {
            //TODO:서버에 플레이스트 정보 보내기
            dismiss()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    companion object {
        const val TAG = "FragmentPlaylistAddBottomSheet"
        fun newInstance(): FragmentPlaylistAddBottomSheet{
            return FragmentPlaylistAddBottomSheet()
        }
    }
}