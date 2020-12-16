package com.trallerd.quiz.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
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

    override fun onClick(v : View?) {
        when(v!!.id){
            R.id.btnLogin->{
                if (!TextUtils.isEmpty(emailLogin.text.toString())) {
                    if (!TextUtils.isEmpty(passwordLogin.text.toString())) {
                        userAdapter.login(
                            emailLogin.text.toString() ,
                            passwordLogin.text.toString()
                        ) { statusAPI ->
                            if (statusAPI == "success") {
                                val intent = Intent(this.context , MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this.context ,
                                    R.string.invalid_user ,
                                    Toast.LENGTH_SHORT
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