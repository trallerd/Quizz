package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.game.end.EndGameResponse
import com.trallerd.quiz.models.game.start.GameResponse
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    @GET("games")
    fun startRandom(@Header("Authorization") authorization: String): Call<GameResponse>

    @GET("games")
    fun start(@Query("difficulty") difficulty: String,@Query("category_id") category_id: Long, @Header("Authorization") authorization: String): Call<GameResponse>

   @DELETE("games")
   fun endGame(@Header("Authorization") authorization: String): Call<EndGameResponse>
}