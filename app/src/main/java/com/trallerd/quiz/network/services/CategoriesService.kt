package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.Categories.CategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesService {
    @GET("categories")
    fun getCategories(): Call<CategoriesResponse>
}