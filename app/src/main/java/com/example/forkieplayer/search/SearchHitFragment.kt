package com.example.forkieplayer.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentSearchHitBinding

class SearchHitFragment : Fragment() {

    lateinit var searchActivity: SearchActivity
    lateinit var adapter: SearchHitAdapter
    lateinit var hitViewModel: SearchViewModel

    val datas = mutableListOf<SearchHitData>()

    // context 획득
    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchActivity = context as SearchActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchHitBinding.inflate(inflater, container, false)

        hitViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        subscribeHitViewModel()
        callHitAPI()

        binding.recyclerHit.layoutManager = LinearLayoutManager(searchActivity)
        adapter = SearchHitAdapter(datas)
        binding.recyclerHit.adapter = adapter

        return binding.root
    }

    private fun subscribeHitViewModel() {
        hitViewModel.hitVideoList.observe(searchActivity) {
            it.forEach { i ->
                datas.add(SearchHitData(i.videoId, i.title, i.thumbnail, i.channelTitle, i.channelImage, i.duration))
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun callHitAPI() = ViewModelProvider(this).get(SearchViewModel::class.java).requestHitVideos()
}