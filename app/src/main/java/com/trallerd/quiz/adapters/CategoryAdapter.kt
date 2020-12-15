package com.trallerd.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trallerd.quiz.R
import com.trallerd.quiz.dao.CategoriesDAO
import com.trallerd.quiz.models.Category
import kotlinx.android.synthetic.main.recyclerview_category.view.*

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private val categoriesDAO = CategoriesDAO()
    private var categories = listOf<Category>()

    init {
        categoriesDAO.getCategories { categoriesAPI ->
            categories = categoriesAPI.data.categories
            notifyDataSetChanged()
        }
    }


    override fun getItemViewType(position : Int) : Int {
        return R.layout.recyclerview_category
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) =
        CategoryHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType , parent , false)
        )

    override fun onBindViewHolder(holder : CategoryHolder , position : Int) {
        val category = categories[position]
        holder.fillView(category)
    }

    override fun getItemCount() : Int {
        return categories.size
    }

    inner class CategoryHolder(item : View) : RecyclerView.ViewHolder(item) {
        fun fillView(category : Category) {
            itemView.txtCategory.text = category.name
        }
    }


}