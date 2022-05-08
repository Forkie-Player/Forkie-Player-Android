package com.example.forkieplayer.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.forkieplayer.databinding.ActivityIntroBinding
import com.example.forkieplayer.profile.ProfileActivity

class IntroActivity : AppCompatActivity() {

    lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 다크모드 제한
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.apply {
            btnSignin.setOnClickListener {
                val intent = Intent(this@IntroActivity, SignInActivity::class.java)
                startActivity(intent)
            }
            btnSignup.setOnClickListener {
                val intent = Intent(this@IntroActivity, SignUpEmailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}