package com.example.forkieplayer.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class PageAdapter(fragmentActivity: FragmentActivity, fragments: ArrayList<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    var fragments = ArrayList<Fragment>()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentOnboardingOne()
            1 -> FragmentOnboardingTwo()
            2 -> FragmentOnboardingThree()
            3 -> FragmentOnboardingFour()
            else -> FragmentOnboardingFive()
        }
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    init {
        this.fragments = fragments
    }
}