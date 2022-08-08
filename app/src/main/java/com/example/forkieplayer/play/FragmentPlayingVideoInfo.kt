package com.example.forkieplayer.play

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forkieplayer.databinding.FragmentPlayingVideoInfoBinding

class FragmentPlayingVideoInfo : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlayingVideoInfoBinding.inflate(inflater, container, false)

        binding.detailInfo.setOnClickListener {
            binding.groupShort.visibility = View.GONE
            binding.groupDetail.visibility = View.VISIBLE
        }

        binding.shortInfoD.setOnClickListener {
            binding.groupDetail.visibility = View.GONE
            binding.groupShort.visibility = View.VISIBLE
        }

        binding.cvPlaylistBar.setOnClickListener(View.OnClickListener {
            (activity as PlayActivity).changePlaylist()
        })

        return binding.root
    }
//        fun changeVideo(title: String, channelImg: Int, channelTitle: String, startTime:Float, endTime:Float) {
//        binding.tvTitle.text = title
//        binding.tvTitleD.text = title
//        binding.ivProfile.setImageResource(channelImg)
//        binding.ivProfileD.setImageResource(channelImg)
//        binding.tvName.text = channelTitle
//        binding.tvNameD.text = channelTitle
//        binding.tvCaptureStart.text = "${(startTime/3600).toInt()}:${((startTime%3600)/60).toInt()}:${((startTime%3600)%60).toInt()}"
//        binding.tvCaptureEnd.text = "${(endTime/3600).toInt()}:${((endTime%3600)/60).toInt()}:${((endTime%3600)%60).toInt()}"
//    }
}