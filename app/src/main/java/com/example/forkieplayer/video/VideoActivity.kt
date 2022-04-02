package com.example.forkieplayer.video

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityVideoBinding
import com.google.android.material.slider.RangeSlider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.text.DecimalFormat

class VideoActivity : AppCompatActivity() {

    lateinit var binding: ActivityVideoBinding
    lateinit var myYoutubePlayer: YouTubePlayer
    val timeFormat = DecimalFormat("00")
    val videoId = "nq0IApxv6Cg"
    val videoLength = 3287.0f
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

        lifecycle.addObserver(binding.youtubePlayer)

        // 처음 로딩할 때 세팅
        setInitial()

        // 슬라이더 움직일 때
        sliderMove()

        // TODO: EditText 변경했을 때
        timeTextChange()

        // TODO: 추가 버튼 눌렀을 때
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

    fun setInitial() {
        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                myYoutubePlayer = youTubePlayer
                myYoutubePlayer.loadVideo(videoId, 0f)

                changeStart(0.0f)
                changeEnd(videoLength)
            }
        })

        binding.slider.valueFrom = 0.0f
        binding.slider.valueTo = videoLength
        binding.slider.values = arrayListOf(0.0f, videoLength)
    }

    fun sliderMove() {
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

    fun timeTextChange() {
        binding.etStartTimeSec.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false
            var hour = binding.etStartTimeHour.text.toString().toFloat()
            var min = binding.etStartTimeMin.text.toString().toFloat()
            var sec = binding.etStartTimeSec.text.toString().toFloat()

            aStartTime = getPointSec(hour, min, sec)

            if (action == EditorInfo.IME_ACTION_DONE) {
                changeStart(aStartTime)
                binding.slider.values = arrayListOf(aStartTime, aEndTime)
                handled = true
            }
            handled
        }

        binding.etEndTimeSec.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false
            var hour = binding.etEndTimeHour.text.toString().toFloat()
            var min = binding.etEndTimeMin.text.toString().toFloat()
            var sec = binding.etEndTimeSec.text.toString().toFloat()

            aEndTime = getPointSec(hour, min, sec)

            if (action == EditorInfo.IME_ACTION_DONE) {
                changeEnd(aEndTime)
                binding.slider.values = arrayListOf(aStartTime, aEndTime)
                handled = true
            }
            handled
        }
    }

    fun changeStart(time: Float) {
        binding.etStartTimeHour.setText(getHour(time))
        binding.etStartTimeMin.setText(getMin(time))
        binding.etStartTimeSec.setText(getSec(time))

        binding.tvSlideStartHour.text = getHour(time)
        binding.tvSlideStartMin.text = getMin(time)
        binding.tvSlideStartSec.text = getSec(time)

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

        myYoutubePlayer.seekTo(time)
        bEndTime = time
    }

    fun getHour(time: Float): String {
        val hour = timeFormat.format((time/3600).toInt())
        return hour
    }

    fun getMin(time: Float): String {
        val min = timeFormat.format((time%3600/60).toInt())
        return min
    }

    fun getSec(time: Float): String {
        val sec = timeFormat.format((time%3600%60).toInt())
        return sec
    }

    fun getPointSec(hour: Float, min: Float, sec: Float): Float {
        val total = hour * 3600 + min * 60 + sec
        return total
    }
}