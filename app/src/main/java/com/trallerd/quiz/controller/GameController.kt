package com.trallerd.quiz.controller

import android.view.View
import com.trallerd.quiz.Controller
import com.trallerd.quiz.dao.GameDAO
import com.trallerd.quiz.models.category.Category
import com.trallerd.quiz.models.game.end.EndGameResponse
import com.trallerd.quiz.models.game.start.Game
import com.trallerd.quiz.models.game.start.GameResponse

class GameController(view : View) {
    private val gameDAO = GameDAO()


    fun start(done : (game : GameResponse) -> Unit) {
        if (Controller.endGame){
            clean()
        }
        Controller.random = false
        val token = Controller.token!!
        val difficulty = Controller.difficult
        val category = Controller.category.id
        gameDAO.start(difficulty , category , token) { gameAPI ->
            done(gameAPI)
        }
    }



    fun startRandom(done : (game : GameResponse) -> Unit) {
        if (Controller.endGame){
            clean()
        }
        Controller.random = true
        val token = Controller.token!!
        gameDAO.startRandom(token) { gameAPI ->
                done(gameAPI)
        }

    }

    fun endGame(done : (game : EndGameResponse) -> Unit) {
        val token = Controller.token!!.toString()
        gameDAO.endGame(token) { gameAPI ->
            done(gameAPI)
        }
    }

    private fun clean() {
        Controller.difficult = ""
        Controller.category = Category(0 , "")
        Controller.game = Game("" , "" , "" , 0)
        Controller.endGame = false
        Controller.question = ""
        Controller.problem = false
    }


}