package com.example.forkieplayer.sign

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivitySignUpPwdBinding
import com.example.forkieplayer.playlist.MainActivity
import java.util.regex.Pattern

class SignUpPwdActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpPwdBinding
    lateinit var pwd: String
    lateinit var pwdChk: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPwdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

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
                        val intent = Intent(this@SignUpPwdActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        outlinedTextField1.error = " "
                        outlinedTextField2.error = "알파벳, 숫자, 특수문자가 들어가야해요."
                        btnStart.isEnabled = false
                    }
                }
            }
        }
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