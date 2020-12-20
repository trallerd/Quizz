package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.answer.AnswerResponde
import com.trallerd.quiz.models.problems.ProblemResponse
import retrofit2.Call
import retrofit2.http.*

interface ProblemsService {
    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("problems/next")
    fun getNext(@Header("Authorization") authorization: String): Call<ProblemResponse>
    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("/problems/view")
    fun getCurrent(@Header("Authorization") authorization: String): Call<ProblemResponse>
    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("problems/answer")
    fun answer(@Query("answer") answer: Int,@Header("Authorization") authorization: String): Call<AnswerResponde>
}