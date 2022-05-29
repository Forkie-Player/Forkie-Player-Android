package com.example.forkieplayer.sign.signup

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivitySignUpPwdBinding
import com.example.forkieplayer.httpbody.SignUpRequest
import com.example.forkieplayer.playlist.MainActivity
import com.example.forkieplayer.sharedpreference.ForkieApplication
import java.util.regex.Pattern


class SignUpPwdActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpPwdBinding
    lateinit var signUpViewModel: SignUpViewModel
    lateinit var pwd: String
    lateinit var pwdChk: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPwdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginId: String = intent.getStringExtra("loginId").toString()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        subscribeViewModel()

        binding.apply {
            tvTerms1.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tvTerms3.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            etPwdChk.isEnabled = false
            btnStart.isEnabled = false

            etPwd.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    outlinedTextField1.error = null
                    outlinedTextField2.error = null
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    pwd = binding.etPwd.text.toString()
                    etPwdChk.isEnabled = pwd.isNotEmpty()
                }

                override fun afterTextChanged(s: Editable) {
                }
            })

            etPwdChk.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    outlinedTextField1.error = null
                    outlinedTextField2.error = null
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    pwdChk = binding.etPwdChk.text.toString()
                    btnStart.isEnabled = pwdChk.isNotEmpty()
                }

                override fun afterTextChanged(s: Editable) {
                }
            })

            btnStart.setOnClickListener{
                if (pwd != pwdChk) {
                    outlinedTextField1.error = " "
                    outlinedTextField2.error = "비밀번호가 일치하지 않아요."
                    btnStart.isEnabled = false
                } else {
                    if (checkPwdRule()) {
                        outlinedTextField1.error = null
                        outlinedTextField2.error = null
                        btnStart.isEnabled = true
                        val signInfo = getSignUpInfo(loginId)
                        callSignUpAPI(signInfo)
                    } else {
                        outlinedTextField1.error = " "
                        outlinedTextField2.error = "알파벳, 숫자, 특수문자가 들어가야해요."
                        btnStart.isEnabled = false
                    }
                }
            }
        }
    }

    fun responseError(code: Int) {
        when(code) {
            400 ->
                CustomToast.makeText(this,"이미 가입한 회원입니다. 로그인해주세요.")?.show()
        }
    }

    private fun subscribeViewModel() {
        signUpViewModel.signupOkCode.observe(this) {
            if (it) {
                ForkieApplication.prefs.accessToken = signUpViewModel.accessToken
                ForkieApplication.prefs.refreshToken = signUpViewModel.refreshToken
                val intent = Intent(this@SignUpPwdActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                CustomToast.makeText(this, "죄송합니다. 회원 가입 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun callSignUpAPI(signUpInfo: SignUpRequest) = signUpViewModel.requestSignup(signUpInfo, this)

    private fun getSignUpInfo(loginId: String): SignUpRequest {
        return SignUpRequest(
            loginId = loginId,
            password = binding.etPwdChk.text.toString(),
            isPC = false
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_signup, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                super.onOptionsItemSelected(item)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun checkPwdRule(): Boolean {
        val rule = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@!%~*#?&^?_=+\$]).{8,20}.\$"
        val pattern = Pattern.compile(rule)

        return pattern.matcher(binding.etPwdChk.text.toString()).find()
    }
}