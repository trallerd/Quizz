package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.answer.AnswerResponde
import com.trallerd.quiz.models.problems.ProblemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ProblemsService {

    @GET("problems/next")
    fun getNext(@Header("Authorization ") authorization: String): Call<ProblemResponse>

    @GET("/problems/view")
    fun getCurrent(@Header("Authorization ") authorization: String): Call<ProblemResponse>

    @POST("problems/answer")
    fun answer(@Query("answer") answer: Int,@Header("Authorization ") authorization: String): Call<AnswerResponde>
}