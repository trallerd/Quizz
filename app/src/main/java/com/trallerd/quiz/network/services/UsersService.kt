package com.trallerd.quiz.network.services

import com.trallerd.quiz.models.Login.Login
import com.trallerd.quiz.models.Login.LoginResponse
import com.trallerd.quiz.models.Users.User
import com.trallerd.quiz.models.Users.UsersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersService {

    @POST("users")
    @Headers("Content-Type: application/json")
    fun insert(@Body user: User): Call<UsersResponse>

    @POST("auth")
    @Headers("Content-Type: application/json")
    fun login(@Body login : Login): Call<LoginResponse>

}