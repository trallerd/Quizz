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
import com.trallerd.quiz.adapters.UsersAdapter
import kotlinx.android.synthetic.main.fragment_register.*

class Register : Fragment() , View.OnClickListener {
    lateinit var userAdapter : UsersAdapter
    var navController : NavController? = null
    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {

        userAdapter = UsersAdapter()
        return inflater.inflate(R.layout.fragment_register , container , false)
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btnRegister).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnBackRegister).setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v : View?) {
        val build: AlertDialog.Builder = AlertDialog.Builder(activity)
        build.setView(R.layout.activity_loading)
        build.setCancelable(false)
        val load: AlertDialog = build.create()
        when (v!!.id) {
            R.id.btnRegister -> {
                if (!TextUtils.isEmpty(R.id.nameRegister.toString())) {
                    if (!TextUtils.isEmpty(R.id.emailRegister.toString())) {
                        if (!TextUtils.isEmpty(R.id.passwordRegister.toString())) {
                            if (!TextUtils.isEmpty(R.id.confirmPRegister.toString())) {
                                if (passwordRegister.text.toString() == confirmPRegister.text.toString()) {
                                    load.show()

                                    userAdapter.insert(
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

            R.id.btnBackRegister -> activity?.onBackPressed()
        }
    }
}