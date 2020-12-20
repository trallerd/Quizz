package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.categories.CategoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoriesService {
    @GET("categories")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getCategories(): Call<CategoriesResponse>
}