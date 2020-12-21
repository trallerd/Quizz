package com.trallerd.quiz.fragments

import android.content.Context
import android.content.Intent
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
import com.trallerd.quiz.LoginRegisterActivity
import com.trallerd.quiz.R
import com.trallerd.quiz.controller.GameController
import com.trallerd.quiz.models.user.User
import kotlinx.android.synthetic.main.fragment_difficult.view.*

class Difficult : Fragment() {
    lateinit var gameController : GameController
    var navController : NavController? = null
    lateinit var build : AlertDialog.Builder

    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_difficult , container , false)
        build = AlertDialog.Builder(view.context)
            .setView(R.layout.activity_loading)
            .setCancelable(false)

        gameController = GameController(view)
        return view
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        view.btnEasy.setOnClickListener { easy() }
        view.btnMedium.setOnClickListener { medium() }
        view.btnHard.setOnClickListener { hard() }
        view.btnRandom.setOnClickListener { randomGame() }
        view.btnLogout.setOnClickListener { logout() }
    }

    private fun logout() {
        val pref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edt = pref?.edit()
        edt?.putString("email", null)
        edt?.putString("password", null)
        edt?.commit()

        Controller.user = User("","","")
        Controller.user.token = ""

        val intent = Intent(this.context, LoginRegisterActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun randomGame() {
        val load : AlertDialog = build.create()
        load.show()
        gameController.startRandom { game ->
            if (game.status == "success") {
                Controller.game = game.data!!.game
                load.dismiss()
                navController!!.navigate(R.id.action_difficult_to_game)
            }
        }
    }

    private fun hard() {
        Controller.difficult = "hard"
        navController!!.navigate(R.id.action_difficult_to_category)
    }

    private fun medium() {
        Controller.difficult = "medium"
        navController!!.navigate(R.id.action_difficult_to_category)
    }

    private fun easy() {
        Controller.difficult = "easy"
        navController!!.navigate(R.id.action_difficult_to_category)
    }
}
