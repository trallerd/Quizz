package com.trallerd.quiz.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.trallerd.quiz.Controller
import com.trallerd.quiz.R
import com.trallerd.quiz.controller.GameController
import com.trallerd.quiz.models.category.Category
import com.trallerd.quiz.models.game.end.EndGame
import com.trallerd.quiz.models.game.start.Game
import kotlinx.android.synthetic.main.fragment_resume.*

class Resume : Fragment() {
    private lateinit var gameController : GameController
    var navController : NavController? = null


    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_resume , container , false)
        val build: AlertDialog.Builder = AlertDialog.Builder(view.context)
            .setView(R.layout.activity_loading)
            .setCancelable(false)
        val load = build.create()
        setView(view,load)

        return view
    }

    private fun setView(view : View , load : AlertDialog) {

        gameController = GameController(view)
        load.show()
        gameController.endGame { endGame ->
            load.dismiss()
            Controller.endGame = endGame.data!!.game
            if (Controller.endGame.score > 0) {
                valueScoreResume.setTextColor(Color.parseColor("#008000"))
            } else {
                valueScoreResume.setTextColor(Color.parseColor("#ff0000"))
            }
            valueScoreResume.text = Controller.endGame.score.toString()
            valueDifficultResume.text = Controller.difficult
            valueCategoryResume.text = Controller.category.name


            valueEndResume.text =  Controller.endGame.finishedAt
            valueStarResume.text = Controller.endGame.startedAt
        }
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        btnResumeBack.setOnClickListener {
            Controller.difficult = ""
            Controller.category = Category(0 , "")
            Controller.game = Game("" , "" , "" , 0)
            Controller.endGame = EndGame("" , "" , "" , 0)
            Controller.question = ""
            Controller.problem = false

            navController!!.navigate(R.id.action_resume_to_home)

        }
    }

}