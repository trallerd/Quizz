package com.trallerd.quiz.models.Ranking

class Ranking(
    var user: String,
    var score: Long
) {
    var id: Long? = null

    override fun equals(other: Any?) = other is Ranking && this.id == other.id

}