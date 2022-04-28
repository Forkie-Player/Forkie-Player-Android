package com.example.forkieplayer.play

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityPlayBinding
import com.example.forkieplayer.profile.FragmentProfileMenuBottomSheet
import com.example.forkieplayer.video.VideoInfoDetailFragment
import com.example.forkieplayer.video.VideoInfoShortFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class PlayActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlayBinding

    val editVideoFragment = FragmentEditVideoInfo()
    val playlistFragment = FragmentPlaylist()
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()

    lateinit var myYoutubePlayer: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_back)

        lifecycle.addObserver(binding.youtubePlayer)

        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                myYoutubePlayer = youTubePlayer
            }
        })

        transaction.add(R.id.fragment_layout, editVideoFragment)
        transaction.commit()
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

    fun changeEditInfo() {
        val tran = manager.beginTransaction()
        tran.replace(R.id.fragment_layout, editVideoFragment)
        tran.commit()
    }
}