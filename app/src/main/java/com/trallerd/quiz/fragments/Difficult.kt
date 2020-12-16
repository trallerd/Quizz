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
import com.trallerd.quiz.R

class Difficult : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_difficult, container, false)
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
        when(v!!.id){
            R.id.btnEasy->{
                val bundle = bundleOf("difficult" to "easy")
                navController!!.navigate(R.id.action_difficult_to_category,bundle)
            }
            R.id.btnMedium->{
                val bundle = bundleOf("difficult" to "medium")
                navController!!.navigate(R.id.action_difficult_to_category,bundle)
            }
            R.id.btnHard->{
                val bundle = bundleOf("difficult" to "hard")
                navController!!.navigate(R.id.action_difficult_to_category,bundle)
            }
            R.id.btnRandom->{

            }
        }
    }
}