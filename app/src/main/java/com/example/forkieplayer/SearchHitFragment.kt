package com.example.forkieplayer

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forkieplayer.databinding.FragmentSearchHitBinding

class SearchHitFragment : Fragment() {

    lateinit var searchActivity: SearchActivity
    lateinit var adapter: SearchHitAdapter

    // context 획득
    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchActivity = context as SearchActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchHitBinding.inflate(inflater, container, false)

        val datas = mutableListOf<SearchHittData>()
        for (i in 1..5){
            datas.add(SearchHittData(R.drawable.search_hit_thumnail_sample, "10CM의 킬링보이스를 라이브로! - 폰서트, 매트리스, pet, 봄이좋냐??, TV를껐네, Perfect, 입김, 스토커, 사랑은은하수다방에서, 아메리카노 ㅣ 딩고뮤직", "딩고 뮤직 / dingo music"))
        }

        binding.recyclerHit.layoutManager = LinearLayoutManager(searchActivity)
        adapter = SearchHitAdapter(datas)
        binding.recyclerHit.adapter = adapter

        return binding.root
    }
}