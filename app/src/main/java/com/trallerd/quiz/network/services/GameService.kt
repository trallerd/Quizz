package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.game.end.EndGameResponse
import com.trallerd.quiz.models.game.start.GameResponse
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("games")
    fun startRandom(@Header("Authorization") authorization: String): Call<GameResponse>
    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("games")
    fun start(@Query("difficulty") difficulty: String,@Query("category_id") category_id: Long, @Header("Authorization") authorization: String): Call<GameResponse>
    @Headers("Content-Type:application/json; charset=UTF-8")
   @DELETE("games")
   fun endGame(@Header("Authorization") authorization: String): Call<EndGameResponse>
}