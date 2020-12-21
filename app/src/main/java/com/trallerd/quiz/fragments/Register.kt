package com.trallerd.quiz.fragments

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.trallerd.quiz.R
import com.trallerd.quiz.controller.UsersController
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class Register : Fragment() {
    lateinit var userController : UsersController
    var navController : NavController? = null
    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {

        userController = UsersController()
        return inflater.inflate(R.layout.fragment_register , container , false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        view.btnRegister.setOnClickListener { register() }
        view.btnBackRegister.setOnClickListener { activity?.onBackPressed() }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun register() {
        val build : AlertDialog.Builder = AlertDialog.Builder(activity)
        build.setView(R.layout.activity_loading)
        build.setCancelable(false)
        val load : AlertDialog = build.create()
        if (!TextUtils.isEmpty(R.id.nameRegister.toString())) {
            if (!TextUtils.isEmpty(R.id.emailRegister.toString())) {
                if (!TextUtils.isEmpty(R.id.passwordRegister.toString())) {
                    if (!TextUtils.isEmpty(R.id.confirmPRegister.toString())) {
                        if (passwordRegister.text.toString() == confirmPRegister.text.toString()) {
                            load.show()

                            userController.insert(
                                nameRegister.text.toString() ,
                                emailRegister.text.toString() ,
                                passwordRegister.text.toString()
                            ) { statusAPI ->
                                if (statusAPI == "success") {
                                    load.dismiss()
                                    navController!!.navigate(R.id.action_register_to_login)
                                } else {
                                    load.dismiss()
                                    Toast.makeText(
                                        this.context ,
                                        statusAPI ,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                this.context ,
                                R.string.password_error ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            this.context ,
                            R.string.fields_error ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this.context ,
                        R.string.fields_error ,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this.context , R.string.fields_error , Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this.context , R.string.fields_error , Toast.LENGTH_SHORT).show()
        }

    }


}