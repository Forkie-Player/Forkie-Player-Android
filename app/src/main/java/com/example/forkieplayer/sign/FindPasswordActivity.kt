package com.example.forkieplayer.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forkieplayer.databinding.ActivityFindPasswordBinding

class FindPasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}