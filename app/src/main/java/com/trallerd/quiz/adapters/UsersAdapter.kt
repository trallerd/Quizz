package com.trallerd.quiz.adapters

import com.trallerd.quiz.dao.UsersDAO
import com.trallerd.quiz.models.Login.Login
import com.trallerd.quiz.models.Users.User

class UsersAdapter {
    var userDao = UsersDAO()

    fun insert(nome: String, email: String, password: String, done: (status: String)->Unit){
        val user = User(nome,email,password)
        userDao.insert(user){userAPI->
            if (userAPI.status=="success"){
                user.token = userAPI.data.user.token
                done(userAPI.status)
            }else{
                done(userAPI.status)
            }
        }
    }

    fun login(email : String, password : String, done : (status: String) -> Unit){
        val login = Login(email, password)
        userDao.login(login){LoginAPI->
            done(LoginAPI.status)
        }
    }
}