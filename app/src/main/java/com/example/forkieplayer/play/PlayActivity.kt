package com.example.forkieplayer.play

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityPlayBinding
import com.example.forkieplayer.httpbody.PlaylistVideoInfo
import com.example.forkieplayer.playlist.PlaylistAdapter
import com.example.forkieplayer.playlist.PlaylistViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class PlayActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlayBinding
    lateinit var getVideoViewModel: GetVideoViewModel
    lateinit var myYoutubePlayer: YouTubePlayer

    val editVideoFragment = FragmentPlayingVideoInfo()
    val playlistFragment = FragmentPlaylist()
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()
    val videoData = arrayListOf<PlaylistVideoInfo>()

    var playlistId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        playlistId = intent.getLongExtra("id", -1).toLong()

        getVideoViewModel = ViewModelProvider(this).get(GetVideoViewModel::class.java)
        callGetVideoAPI(playlistId)
        subscribeGetViewModel()

        lifecycle.addObserver(binding.youtubePlayer)

        transaction.add(R.id.fragment_layout, editVideoFragment)
        transaction.commit()
    }

    private fun callGetVideoAPI(playlistId: Long) = getVideoViewModel.requestVideoInfo(playlistId)

    private fun subscribeGetViewModel() {
        getVideoViewModel.getVideoOkCode.observe(this){
            if(it) {
                val videoData = getVideoViewModel.videoDataList

                if (videoData.isNullOrEmpty()) {
                    CustomToast.makeText(this, "영상이 존재하지 않습니다")?.show()
                    finish()
                } else {
                    videoData.forEach { i ->
                        videoData.add(i)
                    }
                    setFirstVideo()
                }
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 조회 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun setFirstVideo() {
        var firstvideo: PlaylistVideoInfo = videoData[0]
        binding.apply {
            youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    myYoutubePlayer = youTubePlayer
                    myYoutubePlayer.loadVideo(firstvideo.videoId, 0f)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_play, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                finish()
                super.onOptionsItemSelected(item)
            }
            R.id.menu -> {
                //TODO: 메뉴 눌렀을 때
                super.onOptionsItemSelected(item)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun changeVideo(id: String, startTime: Float) {
        myYoutubePlayer.loadVideo(id, startTime)
    }

    fun changePlaylist() {
        val tran = manager.beginTransaction()
        tran.replace(R.id.fragment_layout, playlistFragment)
        tran.commit()
    }

    fun changeVideoInfo() {
        val tran = manager.beginTransaction()
        tran.replace(R.id.fragment_layout, editVideoFragment)
        tran.commit()
    }
}