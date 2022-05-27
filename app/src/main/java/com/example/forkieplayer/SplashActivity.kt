package com.example.forkieplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.forkieplayer.databinding.ActivitySplashBinding
import com.example.forkieplayer.sign.IntroActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()
        handler.postDelayed({
            showUserTypeSelectView()
        }, 1000)

    }

    private fun showUserTypeSelectView() {
        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        finish()
    }
}