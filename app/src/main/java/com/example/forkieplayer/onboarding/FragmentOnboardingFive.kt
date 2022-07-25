package com.example.forkieplayer.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.forkieplayer.databinding.FragmentOnboardingFiveBinding
import com.example.forkieplayer.playlist.MainActivity

class FragmentOnboardingFive : Fragment() {

    lateinit var onboardingActivity: OnboardingActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onboardingActivity = context as OnboardingActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOnboardingFiveBinding.inflate(inflater, container, false)

        binding.apply {
            btnStart.setOnClickListener {
                val intent = Intent(onboardingActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }
}