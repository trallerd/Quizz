package com.trallerd.quiz.models.users

class User(
    var name: String,
    var email: String,
    var password: String
) {
    var token: String? = null

    override fun equals(other: Any?) = other is User && this.token == other.token

}