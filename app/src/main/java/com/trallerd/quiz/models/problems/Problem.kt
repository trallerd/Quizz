package com.trallerd.quiz.models.problems

import com.trallerd.quiz.models.categories.CategoriesGame

class Problem(
        var question : String ,
        var difficulty : String ,
        var category : CategoriesGame ,
        var answers : AnswersProblem
) {
}