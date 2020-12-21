package com.trallerd.quiz

import android.app.Application
import com.trallerd.quiz.models.category.Category
import com.trallerd.quiz.models.game.end.EndGame
import com.trallerd.quiz.models.game.start.Game
import com.trallerd.quiz.models.problem.AnswerProblem
import com.trallerd.quiz.models.user.User

class Controller: Application() {
    companion object{
        var user = User("","","")
        var difficult = ""
        var category = Category(0,"")
        var game = Game("","", "",0)
        var endGame = EndGame("","","",0)
        var question = ""
        var problem = false
        var answers = listOf<AnswerProblem>()
        var random = false
    }
}