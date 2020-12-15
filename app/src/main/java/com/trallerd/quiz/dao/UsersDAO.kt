package com.trallerd.quiz.dao

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersDAO {
    val usersRetrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
}