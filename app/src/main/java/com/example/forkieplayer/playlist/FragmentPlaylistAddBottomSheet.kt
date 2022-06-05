package com.example.forkieplayer.playlist

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.CustomToast
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

        // 처음에는 버튼 비활성화
        binding.apply {
            btnCreate.isEnabled = false

            etPlaylistTitle.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // edittext에 내용 입력되면 버튼 활성화
                    val message = binding.etPlaylistTitle.text.toString()
                    binding.btnCreate.isEnabled = message.isNotEmpty()

                    // 제목 12자 이상 쓰여지면 토스트 메시지
                    if (message.length == 12) {
                        CustomToast.makeText(mainActivity, "제목은 12자 이하로 입력해주세요.")?.show()
                    }
                }

                override fun afterTextChanged(s: Editable) {
                }
            })

            // 제목 12자 이상 쓰여지지 않도록 설정
            etPlaylistTitle.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(12))

            // 입력 내용 서버에 보내기
            btnCreate.setOnClickListener {
                (activity as MainActivity).addPlaylist(etPlaylistTitle.text.toString())
                dismiss()
            }
        }

        return binding.root
    }

    // 모달 스타일 적용
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