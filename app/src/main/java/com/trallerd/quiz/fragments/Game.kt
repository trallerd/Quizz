package com.trallerd.quiz.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.trallerd.quiz.Controller
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.ProblemAdapter
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class Game : Fragment() {
    private lateinit var problemAdapter : ProblemAdapter
    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_game , container , false)
        setView(view)
        return view
    }

    fun setView(view : View) {
        problemAdapter = ProblemAdapter(listOf())
        if (!Controller.problem) {
            problemAdapter.getNext { problemAPI ->
                Controller.difficult = problemAPI.data!!.problem.difficulty
                Controller.category = problemAPI.data!!.problem.category
                Controller.question = problemAPI.data!!.problem.question
                Controller.answers = problemAPI.data!!.problem.answers
                questionGame.text = problemAPI.data!!.problem.question
                difficultGame.text = problemAPI.data!!.problem.difficulty
                categoryGame.text = problemAPI.data!!.problem.category.name
                if (Controller.game.score > 0) {
                    valueScoreGame.setTextColor(Color.parseColor("#008000"))
                } else if (Controller.game.score <= 0) {
                    valueScoreGame.setTextColor(Color.parseColor("#ff0000"))
                }
                valueScoreGame.text = Controller.game.score.toString()
                view.listAnswers.adapter = ProblemAdapter(problemAPI.data!!.problem.answers)
                Controller.problem = true
            }
        } else {
            problemAdapter.getCurrent { problemAPI ->
                Controller.difficult = problemAPI.data!!.problem.difficulty
                Controller.category = problemAPI.data!!.problem.category
                Controller.question = problemAPI.data!!.problem.question
                Controller.answers = problemAPI.data!!.problem.answers
                questionGame.text = problemAPI.data!!.problem.question
                difficultGame.text = problemAPI.data!!.problem.difficulty
                categoryGame.text = problemAPI.data!!.problem.category.name
                if (Controller.game.score > 0) {
                    valueScoreGame.setTextColor(Color.parseColor("#008000"))
                } else if (Controller.game.score <= 0) {
                    valueScoreGame.setTextColor(Color.parseColor("#ff0000"))
                }
                valueScoreGame.text = Controller.game.score.toString()
                view.listAnswers.adapter = ProblemAdapter(problemAPI.data!!.problem.answers)
            }
        }

        view.listAnswers.layoutManager = LinearLayoutManager(
            context ,
            LinearLayoutManager.VERTICAL ,
            false
        )

    }

}