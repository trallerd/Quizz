package com.trallerd.quiz.models.problem

import com.trallerd.quiz.models.category.Category

class Problem(
        var question : String ,
        var difficulty : String ,
        var category : Category ,
        var answers : List<AnswerProblem>
) {
}