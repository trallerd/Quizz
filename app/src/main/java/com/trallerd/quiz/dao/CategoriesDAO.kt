package com.trallerd.quiz.dao

import android.util.Log
import com.trallerd.quiz.models.category.CategoriesResponse
import com.trallerd.quiz.network.services.CategoriesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesDAO {
    val categoriesRetrofit =
        Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    val categoryService = categoriesRetrofit.create(CategoriesService::class.java)


    fun getCategories(finished : (CategoriesResponse) -> Unit) {
        categoryService.getCategories().enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(call : Call<CategoriesResponse> , response : Response<CategoriesResponse>) {
                if (response.body() != null) {
                    val categories = response.body()!!
                    finished(categories)
                }
            }
            override fun onFailure(call : Call<CategoriesResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }
        })
    }
}

