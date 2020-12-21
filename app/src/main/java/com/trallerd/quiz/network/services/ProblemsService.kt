package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.answer.AnswerResponde
import com.trallerd.quiz.models.problem.ProblemResponse
import retrofit2.Call
import retrofit2.http.*

interface ProblemsService {

    @GET("problems/next")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getNext(@Header("Authorization") authorization : String) : Call<ProblemResponse>

    @GET("/problems/view")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getCurrent(@Header("Authorization") authorization : String) : Call<ProblemResponse>

    @POST("problems/answer")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun answer(
            @Query("answer") answer : Int ,
            @Header("Authorization") authorization : String
    ) : Call<AnswerResponde>
}