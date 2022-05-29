package com.example.forkieplayer.sign.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivitySignInBinding
import com.example.forkieplayer.httpbody.SignInRequest
import com.example.forkieplayer.playlist.MainActivity
import com.example.forkieplayer.sharedpreference.ForkieApplication
import com.example.forkieplayer.sign.findpwd.FindPwdEmailActivity

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        binding.apply {
            btnSignin.isEnabled = false

            etPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // edittext에 내용 입력되면 버튼 활성화
                    val message = etPassword.text.toString()
                    btnSignin.isEnabled = message.isNotEmpty()
                }

                override fun afterTextChanged(s: Editable) {}
            })

            tvFindPwd.setOnClickListener {
                val intent = Intent(this@SignInActivity, FindPwdEmailActivity::class.java)
                startActivity(intent)
            }

            btnSignin.setOnClickListener {
                val signInfo = getSignInInfo()
                callSignInAPI(signInfo)
            }
        }

        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        subscribeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_signin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                finish()
                super.onOptionsItemSelected(item)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun responseError(code: Int) {
        when(code) {
            //TODO: api 명세 다시 받고 수정
            500 ->
                CustomToast.makeText(this, "로그인에 실패했습니다")?.show()
        }
    }

    private fun subscribeViewModel() {
        signInViewModel.signInOkCode.observe(this) {
            if (it) {
                ForkieApplication.prefs.accessToken = signInViewModel.accessToken
                ForkieApplication.prefs.refreshToken = signInViewModel.refreshToken
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                CustomToast.makeText(this, "죄송합니다. 로그인 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun getSignInInfo(): SignInRequest {
        binding.apply {
            return SignInRequest(
                loginId = etEmail.text.toString(),
                password = etPassword.text.toString(),
                isPC = false
            )
        }
    }

    private fun callSignInAPI(signInInfo: SignInRequest) = signInViewModel.requestSignIn(signInInfo, this)
}