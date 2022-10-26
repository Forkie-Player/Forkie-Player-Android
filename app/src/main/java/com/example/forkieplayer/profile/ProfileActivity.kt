package com.example.forkieplayer.profile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityProfileBinding
import java.lang.Exception

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        // 프로필 변경 버튼 누르면 갤러리로 감
        binding.btnSelectProfile.setOnClickListener {
            FragmentProfileImageBottomSheet.newInstance().show(
                this.supportFragmentManager, FragmentProfileImageBottomSheet.TAG
            )
        }
    }

    fun changeImage(uri: Uri) {
        Glide.with(this).load(uri)
            .into(binding.ivProfile)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                finish()
                super.onOptionsItemSelected(item)
            }
            R.id.menu -> {
                FragmentProfileMenuBottomSheet.newInstance().show(
                    this.supportFragmentManager, FragmentProfileMenuBottomSheet.TAG
                )
                super.onOptionsItemSelected(item)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}