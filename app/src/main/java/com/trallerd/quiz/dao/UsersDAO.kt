package com.trallerd.quiz.dao

import android.util.Log
import com.trallerd.quiz.models.login.Login
import com.trallerd.quiz.models.login.LoginResponse
import com.trallerd.quiz.models.user.User
import com.trallerd.quiz.models.user.UsersResponse
import com.trallerd.quiz.network.services.UsersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersDAO {
    val usersRetrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
    val userService = usersRetrofit.create(UsersService::class.java)

    fun insert(user : User, finished: (user: UsersResponse)-> Unit){
        userService.insert(user).enqueue(object : Callback<UsersResponse> {
            override fun onResponse(
                    call : Call<UsersResponse> ,
                    response : Response<UsersResponse>
            ) {
                Log.i("Failure" , response.body().toString())

                if(response.body()!=null){
                    val userAPI = response.body()!!
                    Log.i("Failure" , response.body().toString())

                    finished(userAPI)
                }
            }

            override fun onFailure(call : Call<UsersResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }

        })
    }

    fun login(login : Login, finished : (login: LoginResponse) -> Unit){
        userService.login(login).enqueue(object: Callback<LoginResponse>{
            override fun onResponse(
                    call : Call<LoginResponse> ,
                    response : Response<LoginResponse>
            ) {
                Log.i("Failure" , response.body().toString())

                if(response.body()!=null){
                    Log.i("Failure" , response.body().toString())

                    val loginAPI = response.body()!!
                    finished(loginAPI)
                }
            }

            override fun onFailure(call : Call<LoginResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }

        })
    }
}