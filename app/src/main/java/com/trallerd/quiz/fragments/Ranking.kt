package com.trallerd.quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.RankingAdapter
import kotlinx.android.synthetic.main.fragment_ranking.view.*

class Ranking : Fragment() {
    private lateinit var rankingAdapter : RankingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        rankingAdapter = RankingAdapter()
        view.rankingList.adapter = RankingAdapter()
        view.rankingList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        return view
    }
}