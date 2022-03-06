package com.example.forkieplayer

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.forkieplayer.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프로필 변경 버튼 누르면 갤러리로 감
        binding.btnSelectProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            launcher.launch(intent)
        }

        // 갤러리 앱에서 돌아왔을 때 실행됨
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            try {
                val option = BitmapFactory.Options()
                option.inSampleSize = 5

                val inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                bitmap?.let {
                    binding.ivProfile.setImageBitmap(bitmap)
                } ?: let {

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}