package com.example.forkieplayer.video

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.databinding.FragmentVideoInfoShortBinding


class VideoInfoShortFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoInfoShortBinding.inflate(inflater, container, false)

        binding.detailInfo.setOnClickListener(View.OnClickListener {
            (activity as VideoActivity).showDetail()
        })

        return binding.root
    }
}