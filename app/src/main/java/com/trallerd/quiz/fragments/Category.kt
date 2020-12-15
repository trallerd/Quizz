package com.trallerd.quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.trallerd.quiz.R
import com.trallerd.quiz.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.view.*
import kotlinx.android.synthetic.main.recyclerview_category.view.*

class Category : Fragment() {
    private lateinit var categoryAdapter : CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        categoryAdapter = CategoryAdapter()
        view.listCategory.adapter = CategoryAdapter()
        view.listCategory.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        return view
    }
}