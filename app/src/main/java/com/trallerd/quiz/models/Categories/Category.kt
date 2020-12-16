package com.trallerd.quiz.models.Categories

class Category(
    var name : String
) {
    var id: Long? = null

    override fun equals(other: Any?) = other is Category && this.id == other.id

}