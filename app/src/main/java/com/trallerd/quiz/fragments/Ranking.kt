package com.trallerd.quiz.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.RankingAdapter
import kotlinx.android.synthetic.main.fragment_ranking.view.*

class Ranking : Fragment() {
    private lateinit var rankingAdapter : RankingAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_ranking , container , false)
        rankingAdapter = RankingAdapter(view)
        view.rankingList.adapter = RankingAdapter(view)
        view.rankingList.layoutManager = LinearLayoutManager(
            context ,
            LinearLayoutManager.VERTICAL ,
            false
        )
        return view
    }
}