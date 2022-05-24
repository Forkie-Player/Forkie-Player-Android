package com.example.forkieplayer.sign.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivitySignUpEmailBinding
import java.util.regex.Pattern

class SignUpEmailActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        binding.apply {
            btnNext.isEnabled = false

            etId.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val etId = etId.text
                    if (etId != null) {
                        btnNext.isEnabled = checkEmailRule()
                    }
                }

                override fun afterTextChanged(s: Editable) {}
            })

            btnNext.setOnClickListener {
                val loginId = etId.text.toString()
                val intent = Intent(this@SignUpEmailActivity, SignUpAuthCodeActivity::class.java)
                intent.putExtra("loginId", loginId)
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

    private fun checkEmailRule(): Boolean {
        val rule = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern = Pattern.compile(rule)

        return pattern.matcher(binding.etId.text.toString()).find()
    }
}