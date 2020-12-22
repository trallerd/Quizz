package com.trallerd.quiz.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.trallerd.quiz.Controller
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.ProblemAdapter
import com.trallerd.quiz.controller.ProblemController
import com.trallerd.quiz.models.category.Category
import com.trallerd.quiz.models.game.end.EndGame
import com.trallerd.quiz.models.game.start.Game
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class Game : Fragment() {
    private lateinit var problemAdapter : ProblemAdapter
    val problemController = ProblemController()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_game , container , false)
        setView(view)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setView(view : View) {
        if (!Controller.problem) {
            getNext(view)
        } else {
           getCurrent(view)
        }
        view.listAnswers.layoutManager = LinearLayoutManager(
            context ,
            LinearLayoutManager.VERTICAL ,
            false
        )

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getCurrent(view : View) {
        val build: AlertDialog.Builder = AlertDialog.Builder(activity)
        build.setView(R.layout.activity_loading)
        build.setCancelable(false)
        val load: AlertDialog = build.create()
        problemController.getCurrent { problemAPI ->
            openQuestion(view)
            Controller.difficult = problemAPI.data!!.problem.difficulty
            Controller.category = problemAPI.data!!.problem.category
            Controller.question = problemAPI.data!!.problem.question
            Controller.answers = problemAPI.data!!.problem.answers
            questionGame.text = problemAPI.data!!.problem.question
            difficultGame.text = problemAPI.data!!.problem.difficulty
            categoryGame.text = problemAPI.data!!.problem.category.name
            if (Controller.game.score > 0) {
                valueScoreGame.setTextColor(Color.parseColor("#008000"))
            } else if (Controller.game.score < 0) {
                valueScoreGame.setTextColor(Color.parseColor("#ff0000"))
            }
            valueScoreGame.text = Controller.game.score.toString()
            view.listAnswers.adapter = ProblemAdapter(problemAPI.data!!.problem.answers , view)
            load.dismiss()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getNext(view : View) {
        val build: AlertDialog.Builder = AlertDialog.Builder(activity)
        build.setView(R.layout.activity_loading)
        build.setCancelable(false)
        val load: AlertDialog = build.create()
        problemAdapter = ProblemAdapter(listOf(),view)
        load.show()
        problemController.getNext { problemAPI ->
            Controller.difficult = problemAPI.data!!.problem.difficulty
            Controller.category = problemAPI.data!!.problem.category
            Controller.question = problemAPI.data!!.problem.question
            Controller.answers = problemAPI.data!!.problem.answers
            questionGame.text = problemAPI.data!!.problem.question
            difficultGame.text = problemAPI.data!!.problem.difficulty
            categoryGame.text = problemAPI.data!!.problem.category.name
            if (Controller.game.score > 0) {
                valueScoreGame.setTextColor(Color.parseColor("#008000"))
            } else if (Controller.game.score <0) {
                valueScoreGame.setTextColor(Color.parseColor("#ff0000"))
            }
            valueScoreGame.text = Controller.game.score.toString()
            view.listAnswers.adapter = ProblemAdapter(problemAPI.data!!.problem.answers , view)
            Controller.problem = true
            load.dismiss()
        }
    }




    private fun openQuestion(view : View, ) {
        val mAlertDialog = androidx.appcompat.app.AlertDialog.Builder(view.context)
        mAlertDialog.setIcon(R.drawable.question)
        mAlertDialog.setTitle(R.string.app_name)
        mAlertDialog.setMessage(R.string.open_question)
        mAlertDialog.setPositiveButton("Yes") { dialog , id ->
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_game_to_resume)
        }
        mAlertDialog.setNegativeButton("No") { dialog , id ->

        }
        mAlertDialog.show()
    }

}