package com.example.forkieplayer.playlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityMainBinding
import com.example.forkieplayer.httpbody.ChangePlaylistRequest
import com.example.forkieplayer.httpbody.CreatePlaylistRequest
import com.example.forkieplayer.httpbody.DeletePlaylistRequest
import com.example.forkieplayer.httpbody.PlaylistInfo
import com.example.forkieplayer.profile.ProfileActivity
import com.example.forkieplayer.search.SearchActivity


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PlaylistAdapter
    lateinit var fragmentManager: FragmentManager
    lateinit var playlistViewModel: PlaylistViewModel

    var datas = arrayListOf<PlaylistInfo>()
    var deletePosition = -1
    var changePosition = -1
    var changeTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        playlistViewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        callGetPlaylistAPI()

        subscribeGetViewModel()
        subscribeAddViewModel()
        subscribeDeleteViewModel()
        subscribeChangeViewModel()

        // 리사이클러뷰 설정
        fragmentManager = this.supportFragmentManager

        binding.recyclerPlaylist.layoutManager = GridLayoutManager(this, 2)

        // 하단 추가 버튼 클릭시 플레이리스트 편집 fragment 뜨게 함
        binding.btnBottom.setOnClickListener {
            FragmentPlaylistAddBottomSheet.newInstance().show(
                fragmentManager, FragmentPlaylistAddBottomSheet.TAG
            )
        }
    }

    override fun onResume() {
        super.onResume()
        callGetPlaylistAPI()
        subscribeGetViewModel()
    }

    private fun subscribeGetViewModel() {
        playlistViewModel.userPlaylistokCode.observe(this){
            if(it) {
                val playlistData = playlistViewModel.playlistDataList

                if (playlistData.isNullOrEmpty()) {
                    binding.apply {
                        recyclerPlaylist.visibility = View.GONE
                        groupEmpty.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        recyclerPlaylist.visibility = View.VISIBLE
                        groupEmpty.visibility = View.GONE
                    }

                    datas = playlistData
                    adapter = PlaylistAdapter(datas, fragmentManager)
                    binding.recyclerPlaylist.adapter = adapter
                }
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 조회 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun subscribeAddViewModel() {
        playlistViewModel.addPlaylistOkCode.observe(this){
            if(it){
                val newPlaylist = playlistViewModel.newPlaylist
                adapter.addData(PlaylistInfo(newPlaylist.id, newPlaylist.thumbnail, newPlaylist.title, newPlaylist.playCount))
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 추가 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun subscribeDeleteViewModel() {
        playlistViewModel.deletePlaylistOkCode.observe(this){
            if(it){
                adapter.deleteData(deletePosition)
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 삭제 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun subscribeChangeViewModel() {
        playlistViewModel.changePlaylistOkCode.observe(this){
            if(it){
                adapter.changeData(changePosition, changeTitle)
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 제목 변경 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun callGetPlaylistAPI() = playlistViewModel.requestUserPlaylistInfo()

    private fun callCreatePlaylistAPI(createPlaylistInfo: CreatePlaylistRequest) = playlistViewModel.requestCreatePlaylist(createPlaylistInfo)

    private fun callDeletePlaylistAPI(deletePlaylistInfo: DeletePlaylistRequest) = playlistViewModel.requestDeletePlaylist(deletePlaylistInfo)

    private fun callChangePlaylistAPI(changePlaylistInfo: ChangePlaylistRequest) = playlistViewModel.requestChangePlaylist(changePlaylistInfo)

    fun addPlaylist(title: String) {
        val createPlaylistInfo = CreatePlaylistRequest(title = title)
        callCreatePlaylistAPI(createPlaylistInfo)
    }

    fun deletePlaylist(id: Long, position: Int) {
        val deletePlaylistInfo = DeletePlaylistRequest(playlistId = id)
        callDeletePlaylistAPI(deletePlaylistInfo)
        deletePosition = position
    }

    fun changePlaylist(id: Long, position: Int, newTitle: String) {
        val changePlaylistInfo = ChangePlaylistRequest(playlistId = id, title = newTitle)
        callChangePlaylistAPI(changePlaylistInfo)
        changePosition = position
        changeTitle = newTitle
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId) {
            R.id.mypage -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                super.onOptionsItemSelected(item)
            }
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                super.onOptionsItemSelected(item)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}