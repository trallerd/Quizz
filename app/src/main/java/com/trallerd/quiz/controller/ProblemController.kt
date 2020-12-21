package com.trallerd.quiz.controller

import com.trallerd.quiz.Controller
import com.trallerd.quiz.dao.ProblemDAO
import com.trallerd.quiz.models.problem.ProblemResponse

class ProblemController {
    private val problemDAO = ProblemDAO()

    fun getNext(done : (ProblemResponse) -> Unit) {
        val token = Controller.user.token!!
        problemDAO.getNext(token) { problemAPI ->
            done(problemAPI)
        }
    }

    fun getCurrent(done : (ProblemResponse) -> Unit) {
        val token = Controller.user.token!!
        problemDAO.getCurrent(token) { problemAPI ->
            done(problemAPI)
        }
    }

    fun answer(answer : Int , done : (answer : Int) -> Unit) {
        val token = Controller.user.token!!
        problemDAO.answer(token , answer) { answerAPI ->
            done(answerAPI.data!!.answer.correctAnswer.order)
        }
    }

}