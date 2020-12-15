package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.RankingResponse
import retrofit2.Call
import retrofit2.http.GET

interface RankingService {
    @GET("ranking")
    fun getRanking(): Call<RankingResponse>
}