package com.example.forkieplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import com.example.forkieplayer.databinding.ActivitySplashBinding
import com.example.forkieplayer.onboarding.OnboardingActivity
import com.example.forkieplayer.playlist.MainActivity
import com.example.forkieplayer.sign.IntroActivity
import com.example.forkieplayer.sign.signin.SignInActivity
import javax.security.auth.login.LoginException

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 다크모드 제한
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        handler = Handler()
        handler.postDelayed({
            showUserTypeSelectView()
        }, 1000)

    }

    private fun showUserTypeSelectView() {
        //TODO : 로그인 구현할 때 or 구현 완료되면 인트로로 가게 하기
//        val intent = Intent(this, IntroActivity::class.java)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        finish()
    }
}