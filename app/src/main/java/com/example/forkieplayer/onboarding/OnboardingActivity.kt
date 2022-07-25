package com.example.forkieplayer.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.forkieplayer.databinding.ActivityOnboardingBinding
import com.example.forkieplayer.playlist.MainActivity


class OnboardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnboardingBinding
    lateinit var pageAdapter: PageAdapter
    var fragList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //프래그먼트 리스트에 등록
        fragList.add(FragmentOnboardingOne())
        fragList.add(FragmentOnboardingTwo())
        fragList.add(FragmentOnboardingThree())
        fragList.add(FragmentOnboardingFour())
        fragList.add(FragmentOnboardingFive())

        pageAdapter = PageAdapter(this, fragList)
        binding.apply {
            viewPager.adapter = pageAdapter
            dotsIndicator.setViewPager2(viewPager)

            ivSkip.setOnClickListener {
                val intent = Intent(this@OnboardingActivity, MainActivity::class.java);
                startActivity(intent);
                finish();
            }
        }
    }
}