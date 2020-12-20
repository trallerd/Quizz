package com.trallerd.quiz

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_internet_check.*
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class InternetCheck : Fragment() {
    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_internet_check , container , false)
    }


    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        checkInternetAvailibility(view)
    }
    fun checkInternetAvailibility(view : View) {
        if (isInternetAvailable) {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_internetCheck_to_login)
        } else {
            txtError.text = getString(R.string.conectivity_error)
            Toast.makeText(context , "Internet Not Connected" , Toast.LENGTH_LONG)
                .show()
        }
    }

    val isInternetAvailable : Boolean
        get() = try {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        } catch (e : Exception) {
            false
        }

    internal inner class IsInternetActive() :
        AsyncTask<Void? , Void? , String?>() {
        var `is` : InputStream? = null
        var json = "Fail"
        protected override fun doInBackground(vararg params : Void?) : String? {
            try {
                val strUrl =
                    URL("google.com")
                val connection : URLConnection = strUrl.openConnection()
                connection.setDoOutput(true)
                `is` = connection.getInputStream()
                json = "Success"
            } catch (e : Exception) {
                e.printStackTrace()
                json = "Fail"
            }
            return json
        }

        override fun onPostExecute(result : String?) {
            if (result != null) {
                if (result == "Fail") {
                    Toast.makeText(
                        context ,
                        "Internet Not Active" ,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context ,
                        "Internet Active $result" ,
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(context , "Internet Not Active" , Toast.LENGTH_LONG)
                    .show()
            }
        }

        override fun onPreExecute() {
            Toast.makeText(context , "Validating Internet" , Toast.LENGTH_LONG).show()
            super.onPreExecute()
        }
    }


}