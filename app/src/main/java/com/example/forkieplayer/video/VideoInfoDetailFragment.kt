package com.example.forkieplayer.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forkieplayer.databinding.FragmentVideoInfoDetailBinding

class VideoInfoDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoInfoDetailBinding.inflate(inflater, container, false)

        binding.shortInfo.setOnClickListener(View.OnClickListener {
            (activity as VideoActivity).showShort()
        })

        return binding.root
    }
}