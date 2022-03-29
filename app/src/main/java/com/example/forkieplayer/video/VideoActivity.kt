package com.example.forkieplayer.video

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        var bStartTime = 0.0f
        var bEndTime = 3287.0f
        var aStartTime = 0.0f
        var aEndTime = 3287.0f

        // 슬라이더 움직일 때
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
                    myYoutubePlayer.seekTo(aStartTime)
                    bStartTime = aStartTime
                }

                // endTime만 변한 경우
                if(bStartTime==aStartTime && bEndTime!=aEndTime) {
                    changeEnd(aEndTime)
                    myYoutubePlayer.seekTo(aEndTime)
                    bEndTime = aEndTime
                }
            }
        })

        // 추가 버튼 눌렀을 때
        binding.btnAdd.setOnClickListener {
            val startTime = binding.tvStartTime.text.toString()
            val endTime = binding.tvEndTime.text.toString()
            Toast.makeText(this, "startTime : $startTime\nendTime: $endTime", Toast.LENGTH_SHORT).show()
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

    private fun changeStart(time: Float) {
        binding.tvStartTime.text = getTime(getHour(time), getMin(time), getSec(time))
        binding.tvSlideStart.text = getTime(getHour(time), getMin(time), getSec(time))
    }

    private fun changeEnd(time: Float) {
        binding.tvEndTime.text = getTime(getHour(time), getMin(time), getSec(time))
        binding.tvSlideEnd.text = getTime(getHour(time), getMin(time), getSec(time))
    }

    fun getTime(hour: String, min:String, sec: String): String{
        return "${hour}:${min}:${sec}"
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
}