package com.trallerd.quiz.models.problems

import com.trallerd.quiz.models.categories.Category

class Problem(
        var question : String ,
        var difficulty : String ,
        var category : Category ,
        var answers : List<AnswerProblem>
) {
}