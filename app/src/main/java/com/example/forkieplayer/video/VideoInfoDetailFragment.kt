package com.example.forkieplayer.video

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentVideoInfoDetailBinding
import com.example.forkieplayer.search.SearchActivity

class VideoInfoDetailFragment : Fragment() {

    lateinit var videoActivity: VideoActivity

    // context 획득
    override fun onAttach(context: Context) {
        super.onAttach(context)
        videoActivity = context as VideoActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoInfoDetailBinding.inflate(inflater, container, false)

        binding.shortInfo.setOnClickListener(View.OnClickListener {
            (activity as VideoActivity).showShort()
        })

        binding.tvTitle.text = videoActivity.title
        Glide.with(videoActivity)
            .load(videoActivity.channelImg)
            .into(binding.ivProfile)
        binding.tvName.text = videoActivity.channelTitle

        return binding.root
    }
}