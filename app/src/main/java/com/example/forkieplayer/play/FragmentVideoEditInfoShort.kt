package com.example.forkieplayer.play

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentVideoEditInfoDetailBinding
import com.example.forkieplayer.databinding.FragmentVideoEditInfoShortBinding

class FragmentVideoEditInfoShort : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoEditInfoShortBinding.inflate(inflater, container, false)

        binding.detailInfo.setOnClickListener(View.OnClickListener {
            (activity as PlayEditActivity).showShort()
        })

        return binding.root
    }
}