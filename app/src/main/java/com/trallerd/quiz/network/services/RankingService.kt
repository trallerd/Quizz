package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.ranking.RankingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RankingService {
    @GET("ranking")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getRanking(): Call<RankingResponse>
}