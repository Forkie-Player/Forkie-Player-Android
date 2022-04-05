package com.example.forkieplayer.play

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forkieplayer.databinding.FragmentEditVideoInfoBinding

class FragmentEditVideoInfo : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEditVideoInfoBinding.inflate(inflater, container, false)

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
}