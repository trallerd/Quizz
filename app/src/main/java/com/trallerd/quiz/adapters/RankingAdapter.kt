package com.trallerd.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trallerd.quiz.R
import com.trallerd.quiz.dao.RankingDAO
import com.trallerd.quiz.models.Ranking.Ranking
import kotlinx.android.synthetic.main.recyclerview_ranking.view.*

class RankingAdapter(): RecyclerView.Adapter<RankingAdapter.RankingHolder>() {
    private val rankingDAO = RankingDAO()
    private var rankings = listOf<Ranking>()

    init {
        rankingDAO.getRanking { rankingAPI->
            rankings = rankingAPI.data.ranking
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position : Int) : Int {
        return R.layout.recyclerview_ranking
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) =
        RankingHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType,parent,false)
        )

    override fun onBindViewHolder(holder : RankingHolder , position : Int) {
        val ranking = rankings[position]
        holder.fillView(ranking)
    }

    override fun getItemCount() = rankings.size

    inner class RankingHolder(item: View): RecyclerView.ViewHolder(item){
        fun fillView(ranking : Ranking){
            itemView.userRanking.text = ranking.user
            itemView.scoreRanking.text = ranking.score.toString()
        }
    }


}