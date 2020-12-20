package com.trallerd.quiz.fragments

import android.app.AlertDialog
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.trallerd.quiz.Loading
import com.trallerd.quiz.MainActivity
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.UsersAdapter
import kotlinx.android.synthetic.main.fragment_login.*

class Login : Fragment(), View.OnClickListener {
    lateinit var userAdapter : UsersAdapter
    var navController: NavController? = null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        userAdapter = UsersAdapter()
        return inflater.inflate(R.layout.fragment_login,container,false)
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnRegisterLogin).setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v : View?) {
        val build: AlertDialog.Builder = AlertDialog.Builder(activity)
        build.setView(R.layout.activity_loading)
        build.setCancelable(false)
        when(v!!.id){
            R.id.btnLogin->{
                if (!TextUtils.isEmpty(emailLogin.text.toString())) {
                    if (!TextUtils.isEmpty(passwordLogin.text.toString())) {
                        val load: AlertDialog = build.create()
                        load.show()
                        userAdapter.login(
                            emailLogin.text.toString() ,
                            passwordLogin.text.toString()
                        ) { statusAPI ->
                            if (statusAPI == "success") {
                                load.dismiss()
                                val intent = Intent(this.context , MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                load.dismiss()
                                Toast.makeText(
                                    this.context ,
                                    R.string.invalid_user ,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }else{
                        Toast.makeText(this.context,R.string.fields_error,Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this.context,R.string.fields_error,Toast.LENGTH_SHORT).show()
                }

            }
            R.id.btnRegisterLogin-> navController!!.navigate(R.id.action_login_to_register)
        }
    }






}