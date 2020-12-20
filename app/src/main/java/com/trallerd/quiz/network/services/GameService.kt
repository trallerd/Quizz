package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.game.end.EndGameResponse
import com.trallerd.quiz.models.game.start.GameResponse
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    @GET("games")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun startRandom(@Header("Authorization") authorization : String) : Call<GameResponse>

    @GET("games")
    @Headers("Content-Type:application/json; charset=UTF-8")

    fun start(
            @Query("difficulty") difficulty : String ,
            @Query("category_id") category_id : Long ,
            @Header("Authorization") authorization : String
    ) : Call<GameResponse>

    @DELETE("games")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun endGame(@Header("Authorization") authorization : String) : Call<EndGameResponse>
}