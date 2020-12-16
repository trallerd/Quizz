package com.trallerd.quiz.dao

import android.util.Log
import com.trallerd.quiz.models.answer.AnswerResponde
import com.trallerd.quiz.models.problems.ProblemResponse
import com.trallerd.quiz.network.services.ProblemsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProblemDAO {
    val problemRetrofit =
        Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    val problemService = problemRetrofit.create(ProblemsService::class.java)


    fun getNext(token:String, finished:(ProblemResponse)->Unit){
        problemService.getNext(token).enqueue(object : Callback<ProblemResponse>{
            override fun onResponse(
                    call : Call<ProblemResponse> ,
                    response : Response<ProblemResponse>
            ) {
                if(response.body()!=null){
                    finished(response.body()!!)
                }
            }

            override fun onFailure(call : Call<ProblemResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }
        })

    }
    fun getCurrent(token:String, finished:(ProblemResponse)->Unit){
        problemService.getCurrent(token).enqueue(object : Callback<ProblemResponse>{
            override fun onResponse(
                    call : Call<ProblemResponse> ,
                    response : Response<ProblemResponse>
            ) {
                if(response.body()!=null){
                    finished(response.body()!!)
                }
            }

            override fun onFailure(call : Call<ProblemResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }
        })

    }
    fun answer(token:String, answer: Int,finished:(AnswerResponde)->Unit){
        problemService.answer(answer,token).enqueue(object : Callback<AnswerResponde>{
            override fun onResponse(
                    call : Call<AnswerResponde> ,
                    response : Response<AnswerResponde>
            ) {
                if(response.body()!=null){
                    finished(response.body()!!)
                }
            }

            override fun onFailure(call : Call<AnswerResponde> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }
        })

    }
}