package com.example.forkieplayer.play

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentVideoEditInfoDetailBinding
import com.example.forkieplayer.databinding.FragmentVideoInfoShortBinding

class FragmentVideoEditInfoDetail : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoEditInfoDetailBinding.inflate(inflater, container, false)

        binding.shortInfo.setOnClickListener(View.OnClickListener {
            (activity as PlayEditActivity).showDetail()
        })

        return binding.root
    }
}