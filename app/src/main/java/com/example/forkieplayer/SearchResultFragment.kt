package com.example.forkieplayer

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forkieplayer.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {

    lateinit var searchActivity: SearchActivity
    lateinit var adapter: SearchResultAdapter

    // context 획득
    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchActivity = context as SearchActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        val datas = mutableListOf<SearchResultData>()
        for (i in 1..10){
            datas.add(SearchResultData(R.drawable.search_result_thumbnail_sample, "[Playlist] 눈 위에 쓰는 이적의 목소리", R.drawable.search_profile_sample, "my blue valentine"))
        }

        binding.recyclerResult.layoutManager = LinearLayoutManager(searchActivity)
        adapter = SearchResultAdapter(datas)
        binding.recyclerResult.adapter = adapter

        return binding.root
    }
}