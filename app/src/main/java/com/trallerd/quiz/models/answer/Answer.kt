package com.trallerd.quiz.models.answer

import com.google.gson.annotations.SerializedName

class Answer(
        var status: String,
        @SerializedName("correct_answer") var correctAnswer : CorrectAnswer,
        var score: Int
) {
}