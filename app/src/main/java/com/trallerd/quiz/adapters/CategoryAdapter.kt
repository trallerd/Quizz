package com.trallerd.quiz.adapters

import android.app.AlertDialog
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.trallerd.quiz.Controller
import com.trallerd.quiz.R
import com.trallerd.quiz.dao.CategoriesDAO
import com.trallerd.quiz.models.category.Category
import kotlinx.android.synthetic.main.recyclerview_category.view.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CategoryAdapter(view : View) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private val categoriesDAO = CategoriesDAO()
    private val gameAdapter = GameAdapter(view)
    private var categories = listOf<Category>()
    val build : AlertDialog.Builder = AlertDialog.Builder(view.context)
        .setView(R.layout.activity_loading)
        .setCancelable(false)
        .setIcon(R.drawable.loading)
    val load : AlertDialog = build.create()

    init {

        load.show()
        categoriesDAO.getCategories { categoriesAPI ->
            categories = categoriesAPI.data.categories
            notifyDataSetChanged()
            load.dismiss()
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
            itemView.setOnClickListener {
                load.show()
                Controller.category = category
                gameAdapter.start {game->
                    load.dismiss()
                    Controller.game = game.data!!.game
                    val navController = Navigation.findNavController(it)
                    navController.navigate(R.id.action_category_to_game)
                }

            }
        }
    }


}