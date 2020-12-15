package com.trallerd.quiz.models

class Users(
    var name: String,
    var email: String,
    var password: String
) {
    var id: Long? = null

    override fun equals(other: Any?) = other is Users && this.id == other.id

}