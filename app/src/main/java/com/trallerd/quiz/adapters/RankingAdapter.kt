package com.trallerd.quiz.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trallerd.quiz.R
import com.trallerd.quiz.dao.RankingDAO
import com.trallerd.quiz.models.ranking.Ranking
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
            if (ranking.score<0){
                itemView.scoreRanking.setTextColor(Color.parseColor("#ff0000"))
            }else if (ranking.score>0){
                itemView.scoreRanking.setTextColor(Color.parseColor("#008000"))
            }
            itemView.scoreRanking.text = ranking.score.toString()
        }
    }


}