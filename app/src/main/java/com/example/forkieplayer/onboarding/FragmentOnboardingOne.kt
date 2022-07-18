package com.example.forkieplayer.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forkieplayer.databinding.FragmentOnboardingOneBinding

class FragmentOnboardingOne : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOnboardingOneBinding.inflate(inflater, container, false)

        return binding.root
    }
}