package com.trallerd.quiz.dao

import com.trallerd.quiz.models.Ranking.RankingResponse
import com.trallerd.quiz.network.services.RankingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RankingDAO {
    val rankingRetrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(
        GsonConverterFactory.create()).build()
    val rankingService = rankingRetrofit.create(RankingService::class.java)

    fun getRanking(finished : (ranking: RankingResponse)->Unit){
        rankingService.getRanking().enqueue(object : Callback<RankingResponse>{
            override fun onResponse(
                    call : Call<RankingResponse> ,
                    response : Response<RankingResponse>
            ) {
                if (response.body() != null) {
                    val ranking = response.body()!!
                    finished(ranking)
                }
            }

            override fun onFailure(call : Call<RankingResponse> , t : Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}