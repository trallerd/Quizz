package com.trallerd.quiz.fragments

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.view.*

class Category : Fragment() {
    private lateinit var categoryAdapter : CategoryAdapter
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        val build: AlertDialog.Builder = AlertDialog.Builder(activity)
        build.setView(R.layout.activity_loading)
        build.setCancelable(false)
        val load: AlertDialog = build.create()
        load.show()
        categoryAdapter = CategoryAdapter(view)
        view.listCategory.adapter = CategoryAdapter(view)
        view.listCategory.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        load.dismiss()
        return view
    }


}