package com.trallerd.quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.trallerd.quiz.Controller
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.GameAdapter

class Difficult : Fragment() , View.OnClickListener {
    val gameAdapter = GameAdapter()
    var navController : NavController? = null
    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_difficult , container , false)
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
                gameAdapter.startRandom {game->
                    if (game.status== "success"){
                        Controller.game = game.data!!.game
                        navController!!.navigate(R.id.action_difficult_to_game)
                    }
                }
            }
        }
    }
}