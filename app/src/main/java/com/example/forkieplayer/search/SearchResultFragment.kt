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

        callSearchAPI(searchText)
        subscribeSearchViewModel()

        binding.recyclerResult.layoutManager = LinearLayoutManager(searchActivity)
        adapter = SearchResultAdapter(datas)
        binding.recyclerResult.adapter = adapter

        return binding.root
    }

    fun getSearchText(search: String) {
        searchText = search
    }

    private fun callSearchAPI(search: String) = ViewModelProvider(this).get(SearchViewModel::class.java).requestSearch(search)

    private fun subscribeSearchViewModel() {
        searchViewModel.searchokCode.observe(searchActivity){
            if(it) {
                val searchResult = searchViewModel.searchResultList ?: arrayListOf()

                if (searchResult.isNullOrEmpty()) {
                    // TODO: 검색 결과 없는 화면 띄우기
                } else {
                    searchResult.forEach { i ->
                        datas.add((SearchResultData(i.thumbnail, i.title, i.channelImage, i.channelTitle)))
                    }
                }
            } else {
                CustomToast.makeText(searchActivity, "죄송합니다. 검색 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }
}