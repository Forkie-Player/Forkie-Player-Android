package com.example.forkieplayer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.example.forkieplayer.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        // fragment 설정
        val hitFragment = SearchHitFragment()
        val resultFragment = SearchResultFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        // 액티비티 실행시 hitFragment 실행
        transaction.add(R.id.fragment_layout, hitFragment)
        transaction.commit()

        // 엔터 눌렀을 때 : x랑 커서 안보이게, 키보드 내려가게, frament_search_result 나오게 설정
        binding.etSearch.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                binding.textInputLayout.isEndIconVisible = false
                binding.etSearch.isCursorVisible = false

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

                val tran = manager.beginTransaction()
                tran.replace(R.id.fragment_layout, resultFragment)
                tran.commit()

                true
            }
            else {
                false
            }
        }

        // edit창 눌렀을 때(재수정) : x랑 커서 보이게
        binding.etSearch.setOnClickListener {
            binding.textInputLayout.isEndIconVisible = true
            binding.etSearch.isCursorVisible = true
        }

        // x 눌렀을 때 : 내용 지워지고 frament_search_hit 나오도록 설정
        binding.textInputLayout.setEndIconOnClickListener {
            binding.etSearch.text!!.clear()
            val tran = manager.beginTransaction()
            tran.replace(R.id.fragment_layout, hitFragment)
            tran.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_search, menu)
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
}