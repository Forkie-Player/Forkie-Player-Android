package com.example.forkieplayer.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.databinding.FragmentSearchResultBinding
import com.example.forkieplayer.httpbody.SearchInfo

class SearchResultFragment : Fragment() {

    lateinit var searchActivity: SearchActivity
    lateinit var adapter: SearchResultAdapter
    lateinit var searchViewModel: SearchViewModel

    var searchText = ""
    val datas = mutableListOf<SearchResultData>()

    // context 획득
    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchActivity = context as SearchActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        subscribeSearchViewModel()
        callSearchAPI(searchText)

        binding.recyclerResult.layoutManager = LinearLayoutManager(searchActivity)
        adapter = SearchResultAdapter(datas)
        binding.recyclerResult.adapter = adapter

        return binding.root
    }

    fun getSearchText(search: String) {
        searchText = search
    }

    fun callSearchAPI(search: String) = ViewModelProvider(this).get(SearchViewModel::class.java).requestSearch(search)

    private fun subscribeSearchViewModel() {
        searchViewModel.searchResultList.observe(searchActivity) {
            it.forEach { i ->
                datas.add(SearchResultData(i.videoId, i.title, i.thumbnail, i.channelTitle, i.channelImage, i.duration))
            }
            adapter.notifyDataSetChanged()
        }
    }
}