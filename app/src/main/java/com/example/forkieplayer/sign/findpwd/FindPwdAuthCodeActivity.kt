package com.example.forkieplayer.sign.findpwd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityFindPwdAuthCodeBinding

class FindPwdAuthCodeActivity : AppCompatActivity() {

    lateinit var binding: ActivityFindPwdAuthCodeBinding
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPwdAuthCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        fragmentManager = this.supportFragmentManager

        setTimer()
        codeInput()

        binding.apply {
            btnNext.isEnabled = false

            // TODO : 서버 연동 후 인증번호 재발송
            btnResend.setOnClickListener {
                FragmentResendAuthCode2.newInstance().show(
                    fragmentManager, FragmentResendAuthCode2.TAG
                )
            }

            btnNext.setOnClickListener {
                // TODO : 인증 번호 맞는지 확인
                val intent = Intent(this@FindPwdAuthCodeActivity, FindPwdActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_signup, menu)
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

    fun setTimer() {
        binding.tvTime.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.forkie_g7))

        object : CountDownTimer(1000 * 300, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val min = (millisUntilFinished / 1000) / 60
                val second = (millisUntilFinished / 1000) % 60

                if (second < 10) {
                    binding.tvTime.text = "0$min:0$second"
                } else {
                    binding.tvTime.text = "0$min:$second"
                }
            }

            override fun onFinish() {
                binding.tvTime.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.forkie_red))
                FragmentTimeOver2.newInstance().show(
                    fragmentManager, FragmentTimeOver2.TAG
                )
            }
        }.start()
    }

    fun codeInput() {
        binding.apply {
            etCode1.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (etCode1.text.toString().length == 1) {
                        etCode2.requestFocus()
                    }
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }
                override fun afterTextChanged(s: Editable) {}
            })

            etCode2.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (etCode2.text.toString().length == 1) {
                        etCode3.requestFocus()
                    }
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }
                override fun afterTextChanged(s: Editable) {}
            })

            etCode3.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (etCode3.text.toString().length == 1) {
                        etCode4.requestFocus()
                    }
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }
                override fun afterTextChanged(s: Editable) {}
            })

            etCode4.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (etCode4.text.toString().length == 1) {
                        etCode5.requestFocus()
                    }
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }
                override fun afterTextChanged(s: Editable) {}
            })

            etCode5.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (etCode4.text.toString().length == 1) {
                        btnNext.isEnabled = true
                    }
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }
                override fun afterTextChanged(s: Editable) {}
            })
        }
    }
}