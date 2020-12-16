package com.trallerd.quiz.models.categories

class Category(
    var name : String
) {
    var id: Long? = null

    override fun equals(other: Any?) = other is Category && this.id == other.id

}