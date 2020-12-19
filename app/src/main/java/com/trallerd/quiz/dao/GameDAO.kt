package com.trallerd.quiz.dao

import android.util.Log
import com.trallerd.quiz.models.game.end.EndGameResponse
import com.trallerd.quiz.models.game.start.GameResponse
import com.trallerd.quiz.network.services.GameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameDAO {
    val gameRetrofit =
        Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    val gameService = gameRetrofit.create(GameService::class.java)


    fun start(difficulty: String , category_id: Long , authorization: String, finished: (GameResponse)->Unit){
        gameService.start(difficulty,category_id,authorization).enqueue(object: Callback<GameResponse>{
            override fun onResponse(call : Call<GameResponse> , response : Response<GameResponse>) {
                Log.i("Failure" , response.body()!!.toString())
                if (response.body()!=null){
                    if (response.body()!!.status=="success"){
                        finished(response.body()!!)

                    }

                }
            }

            override fun onFailure(call : Call<GameResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }

        })
    }

    fun startRandom(authorization : String, finished : (GameResponse) -> Unit){
        gameService.startRandom(authorization).enqueue(object : Callback<GameResponse>{
            override fun onResponse(call : Call<GameResponse> , response : Response<GameResponse>) {
                if (response.body()!=null){
                    if (response.body()!!.status=="success"){
                        Log.i("Failure" , response.body()!!.data!!.game.status)
                        finished(response.body()!!)

                    }

                }
            }

            override fun onFailure(call : Call<GameResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }

        })
    }

    fun endGame(authorization : String, finished : (EndGameResponse) -> Unit){
        gameService.endGame(authorization).enqueue(object : Callback<EndGameResponse>{
            override fun onResponse(
                    call : Call<EndGameResponse> ,
                    response : Response<EndGameResponse>
            ) {


                if (response.body()!=null){
                    if (response.body()!!.status=="success"){
                        Log.i("Failure" , response.body()!!.toString())
                        finished(response.body()!!)

                    }

                }
            }

            override fun onFailure(call : Call<EndGameResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }

        })
    }
}