package com.example.forkieplayer.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentProfileImageBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class FragmentProfileImageBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: FragmentProfileImageBottomSheetBinding
    lateinit var profileActivity: ProfileActivity

    lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    lateinit var file: File
    lateinit var currentPhotoPath: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileActivity = context as ProfileActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileImageBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            layoutRemoveImage.setOnClickListener {
                val basicUri = Uri.parse("android.resource://com.example.forkieplayer/drawable/basic_profile")
                profileActivity.changeImage(basicUri)
            }

            layoutChoiceImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                galleryLauncher.launch(intent)
            }

            // 갤러리 앱에서 돌아왔을 때 실행됨
            galleryLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                try {
                    val uri : Uri = it.data!!.data!!
                    profileActivity.changeImage(uri)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                dismiss()
            }

            layoutTakeImage.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                file = createImageFile()
                //AndroidMenifest에 설정된 URI와 동일한 값으로 설정한다.
                var photoUri = FileProvider.getUriForFile(profileActivity, "com.example.forkieplayer.fileprovider", file)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                cameraLauncher.launch(intent)
            }

            cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                if(it.resultCode == Activity.RESULT_OK){

                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(profileActivity.contentResolver, Uri.fromFile(file))
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        val uri = getImageUri(bitmap)
                        profileActivity.changeImage(uri)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(profileActivity.contentResolver, Uri.fromFile(file))
                        val uri = getImageUri(bitmap)
                        profileActivity.changeImage(uri)
                    }
                }

                dismiss()
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = profileActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path
            currentPhotoPath = absolutePath
        }
    }

    private fun getImageUri(inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        val path = MediaStore.Images.Media.insertImage(profileActivity.contentResolver, inImage, "Title", null);
        return Uri.parse(path);
    }

    // 모달 스타일 적용
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    companion object {
        const val TAG = "FragmentProfileMenuBottomSheet"
        fun newInstance(): FragmentProfileImageBottomSheet{
            return FragmentProfileImageBottomSheet()
        }
    }
}