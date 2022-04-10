package com.example.forkieplayer.video

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityVideoBinding
import com.google.android.material.slider.RangeSlider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.text.DecimalFormat


class VideoActivity : AppCompatActivity() {

    lateinit var binding: ActivityVideoBinding

    val shortFragment = VideoInfoShortFragment()
    val detailFragment = VideoInfoDetailFragment()
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()

    lateinit var myYoutubePlayer: YouTubePlayer
    val videoId = "nq0IApxv6Cg"
    val videoLength = 3287.0f

    val timeFormat = DecimalFormat("00")
    var maxHour = videoLength.toInt() / 3600
    var maxMin = 59
    var maxSec = 59

    var bStartTime = 0.0f
    var bEndTime = videoLength
    var aStartTime = 0.0f
    var aEndTime = videoLength

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        // 액티비티 실행시 hitFragment 실행
        transaction.add(R.id.fragment_layout, shortFragment)
        transaction.commit()

        lifecycle.addObserver(binding.youtubePlayer)

        // 처음 로딩할 때 세팅
        setInitial()

        // 슬라이더 움직일 때
        sliderMove()

        // EditText 변경했을 때
        timeTextChange()

        // TODO: 추가 버튼 눌렀을 때
        binding.btnAdd.setOnClickListener {
            CustomToast.makeText(this, "시작 시간 : ${aStartTime}\n끝 시간 : ${aEndTime}")?.show()
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

    fun showDetail() {
        val tran = manager.beginTransaction()
        tran.replace(R.id.fragment_layout, detailFragment)
        tran.commit()
    }

    fun showShort() {
        val tran = manager.beginTransaction()
        tran.replace(R.id.fragment_layout, shortFragment)
        tran.commit()
    }

    private fun setInitial() {
        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                myYoutubePlayer = youTubePlayer
                myYoutubePlayer.loadVideo(videoId, 0f)

                changeStart(0.0f)
                changeEnd(videoLength)
            }
        })

        setHourIfZero()

        binding.slider.valueFrom = 0.0f
        binding.slider.valueTo = videoLength
        binding.slider.values = arrayListOf(0.0f, videoLength)

        if (maxHour < 1) {
            maxMin = videoLength.toInt() / 60
            if (maxMin < 1) {
                maxSec = videoLength.toInt()
            }
        }
    }

    private fun setHourIfZero() {
        if (getHour(videoLength) == "00") {
            binding.tvSlideStartHour.visibility = View.GONE
            binding.tvSliderDivide1.visibility = View.GONE
            binding.tvSlideEndHour.visibility = View.GONE
            binding.tvSliderDivide3.visibility = View.GONE
            binding.etStartTimeHour.visibility = View.GONE
            binding.tvDivide1.visibility = View.GONE
            binding.etEndTimeHour.visibility = View.GONE
            binding.tvDivide3.visibility = View.GONE
        }
    }

    private fun sliderMove() {
        binding.slider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener{
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
                aStartTime = binding.slider.values[0].toString().toFloat()
                aEndTime = binding.slider.values[1].toString().toFloat()

                // startTime만 변한 경우
                if(bStartTime!=aStartTime && bEndTime==aEndTime) {
                    changeStart(aStartTime)
                }

                // endTime만 변한 경우
                if(bStartTime==aStartTime && bEndTime!=aEndTime) {
                    changeEnd(aEndTime)
                }
            }
        })
    }

    private fun timeTextChange() {
        binding.etStartTimeSec.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false
            val hour = binding.etStartTimeHour.text.toString().toFloat()
            val min = binding.etStartTimeMin.text.toString().toFloat()
            val sec = binding.etStartTimeSec.text.toString().toFloat()
            val time = getPointSec(hour, min, sec)

            if (action == EditorInfo.IME_ACTION_DONE) {
                if (checkValid(hour, min, sec)) {
                    if (time < aEndTime) {
                        aStartTime = time
                        changeStart(aStartTime)
                        handled = true
                    } else {
                        CustomToast.makeText(this, "시작 지점이 끝 지점보다 큽니다.")?.show()
                        binding.etStartTimeMin.setText(getHour(aStartTime))
                        binding.etStartTimeMin.setText(getMin(aStartTime))
                        binding.etStartTimeSec.setText(getSec(aStartTime))
                    }
                }
                else {
                    CustomToast.makeText(this, "입력값이 유효하지 않습니다.")?.show()
                    binding.etStartTimeMin.setText(getHour(aStartTime))
                    binding.etStartTimeMin.setText(getMin(aStartTime))
                    binding.etStartTimeSec.setText(getSec(aStartTime))
                }
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
            handled
        }

        binding.etEndTimeSec.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false
            val hour = binding.etEndTimeHour.text.toString().toFloat()
            val min = binding.etEndTimeMin.text.toString().toFloat()
            val sec = binding.etEndTimeSec.text.toString().toFloat()
            val time = getPointSec(hour, min, sec)

            if (action == EditorInfo.IME_ACTION_DONE) {
                if (checkValid(hour, min, sec)) {
                    if (time > aStartTime) {
                        aEndTime = time
                        changeEnd(aEndTime)
                        handled = true
                    } else {
                        CustomToast.makeText(this, "끝 지점이 시작 지점보다 작습니다.")?.show()
                        binding.etEndTimeHour.setText(getHour(aEndTime))
                        binding.etEndTimeMin.setText(getMin(aEndTime))
                        binding.etEndTimeSec.setText(getSec(aEndTime))
                    }
                }
                else {
                    CustomToast.makeText(this, "입력값이 유효하지 않습니다.")?.show()
                    binding.etEndTimeHour.setText(getHour(aEndTime))
                    binding.etEndTimeMin.setText(getMin(aEndTime))
                    binding.etEndTimeSec.setText(getSec(aEndTime))
                }
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
            handled
        }
    }

    private fun checkValid(hour: Float, min: Float, sec: Float) : Boolean {
        if (hour<=maxHour && min<=maxMin && sec<=maxSec)
            return true
        return false
    }

    fun changeStart(time: Float) {
        binding.etStartTimeHour.setText(getHour(time))
        binding.etStartTimeMin.setText(getMin(time))
        binding.etStartTimeSec.setText(getSec(time))

        binding.tvSlideStartHour.text = getHour(time)
        binding.tvSlideStartMin.text = getMin(time)
        binding.tvSlideStartSec.text = getSec(time)

        binding.slider.values = arrayListOf(aStartTime, aEndTime)
        myYoutubePlayer.seekTo(time)
        bStartTime = time
    }

    fun changeEnd(time: Float) {
        binding.etEndTimeHour.setText(getHour(time))
        binding.etEndTimeMin.setText(getMin(time))
        binding.etEndTimeSec.setText(getSec(time))

        binding.tvSlideEndHour.text = getHour(time)
        binding.tvSlideEndMin.text = getMin(time)
        binding.tvSlideEndSec.text = getSec(time)

        binding.slider.values = arrayListOf(aStartTime, aEndTime)
        myYoutubePlayer.seekTo(time)
        bEndTime = time
    }

    private fun getHour(time: Float): String {
        return timeFormat.format((time / 3600).toInt())
    }

    private fun getMin(time: Float): String {
        return timeFormat.format((time % 3600 / 60).toInt())
    }

    private fun getSec(time: Float): String {
        return timeFormat.format((time % 3600 % 60).toInt())
    }

    private fun getPointSec(hour: Float, min: Float, sec: Float): Float {
        return hour * 3600 + min * 60 + sec
    }
}