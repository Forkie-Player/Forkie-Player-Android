package com.example.forkieplayer.sign

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.forkieplayer.databinding.ActivityIntroBinding
import com.example.forkieplayer.sign.signin.SignInActivity
import com.example.forkieplayer.sign.signup.SignUpEmailActivity

class IntroActivity : AppCompatActivity() {

    lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 다크모드 제한
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.apply {
            ivEmail.setOnClickListener {
                val intent = Intent(this@IntroActivity, SignInActivity::class.java)
                startActivity(intent)
            }
            tvSignup.setOnClickListener {
                val intent = Intent(this@IntroActivity, SignUpEmailActivity::class.java)
                startActivity(intent)
            }
            tvSignup.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }
    }
}