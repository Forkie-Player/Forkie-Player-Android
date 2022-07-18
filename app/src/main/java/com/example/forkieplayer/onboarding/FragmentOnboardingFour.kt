package com.example.forkieplayer.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.forkieplayer.databinding.FragmentOnboardingFourBinding

class FragmentOnboardingFour : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOnboardingFourBinding.inflate(inflater, container, false)

        return binding.root
    }
}