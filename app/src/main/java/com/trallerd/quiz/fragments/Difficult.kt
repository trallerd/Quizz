package com.trallerd.quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.trallerd.quiz.Controller
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.GameAdapter

class Difficult : Fragment() , View.OnClickListener {
    lateinit var gameAdapter : GameAdapter
    var navController : NavController? = null
    lateinit var build : AlertDialog.Builder

    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        val view =  inflater.inflate(R.layout.fragment_difficult , container , false)
        build = AlertDialog.Builder(view.context)
            .setView(R.layout.activity_loading)
            .setCancelable(false)

        gameAdapter = GameAdapter(view)
        return view
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btnEasy).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnMedium).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnHard).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnRandom).setOnClickListener(this)
    }

    override fun onClick(v : View?) {
        when (v!!.id) {
            R.id.btnEasy -> {
                Controller.difficult = "easy"
                navController!!.navigate(R.id.action_difficult_to_category)
            }
            R.id.btnMedium -> {
                Controller.difficult = "medium"
                navController!!.navigate(R.id.action_difficult_to_category)
            }
            R.id.btnHard -> {
                Controller.difficult = "hard"
                navController!!.navigate(R.id.action_difficult_to_category)
            }
            R.id.btnRandom -> {
                val load : AlertDialog = build.create()
                load.show()
                gameAdapter.startRandom {game->
                    if (game.status== "success"){
                        Controller.game = game.data!!.game
                        load.dismiss()
                        navController!!.navigate(R.id.action_difficult_to_game)
                    }
                }
            }
        }
    }
}