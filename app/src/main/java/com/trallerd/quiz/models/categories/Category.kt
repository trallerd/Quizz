package com.trallerd.quiz.models.categories

class Category(
        var id : Long ,
        var name : String
) {


    override fun equals(other : Any?) = other is Category && this.id == other.id

}