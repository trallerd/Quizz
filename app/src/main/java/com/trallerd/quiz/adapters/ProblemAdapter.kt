package com.trallerd.quiz.adapters

import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.trallerd.quiz.Controller
import com.trallerd.quiz.controller.GameController
import com.trallerd.quiz.R
import com.trallerd.quiz.controller.ProblemController
import com.trallerd.quiz.dao.ProblemDAO
import com.trallerd.quiz.models.problem.AnswerProblem
import com.trallerd.quiz.models.problem.ProblemResponse
import kotlinx.android.synthetic.main.recyclerview_answers.view.*


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ProblemAdapter(answers : List<AnswerProblem> , view : View) :
    RecyclerView.Adapter<ProblemAdapter.ProblemHolder>() {
    val build : AlertDialog.Builder = AlertDialog.Builder(view.context)
        .setView(R.layout.activity_loading)
        .setCancelable(false)
    val load : AlertDialog = build.create()
    val problemController = ProblemController()
    private val gameAdapter = GameController(view)
    private var listAnswers = listOf<AnswerProblem>()

    init {
        listAnswers = answers
    }

    override fun getItemViewType(position : Int) : Int {
        return R.layout.recyclerview_answers
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) =
        ProblemHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType , parent , false)
        )

    override fun onBindViewHolder(holder : ProblemHolder , position : Int) {
        val answer = listAnswers[position]
        holder.fillView(answer)
    }

    override fun getItemCount() = listAnswers.size


    inner class ProblemHolder(item : View) : RecyclerView.ViewHolder(item) {
        fun fillView(answer : AnswerProblem) {
            itemView.txtAnswers.text = answer.description
            itemView.setOnClickListener {
                load.show()
                problemController.answer(answer.order) { answerAPI ->
                    load.dismiss()
                    if (answer.order == answerAPI) {
                        itemView.backgroundAnswer.setBackgroundColor(Color.parseColor("#008000"))

                    } else {
                        itemView.backgroundAnswer.setBackgroundColor(Color.parseColor("#ff0000"))
                    }
                    val mAlertDialog = AlertDialog.Builder(itemView.context)
                    mAlertDialog.setIcon(R.drawable.question)
                    mAlertDialog.setTitle(R.string.app_name)
                    mAlertDialog.setMessage(R.string.continue_question)
                    mAlertDialog.setPositiveButton("Yes") { dialog , id ->
                        Controller.problem = false
                        if (Controller.random) {
                            load.show()
                            gameAdapter.startRandom { game ->
                                load.dismiss()
                                Controller.game = game.data!!.game
                                val navController = Navigation.findNavController(it)
                                navController.navigate(R.id.action_gameFragment_self)
                            }
                        } else {
                            load.show()
                            gameAdapter.start { game ->
                                load.dismiss()
                                Controller.game = game.data!!.game
                                val navController = Navigation.findNavController(it)
                                navController.navigate(R.id.action_gameFragment_self)
                            }
                        }


                    }
                    mAlertDialog.setNegativeButton("No") { dialog , id ->
                        val navController = Navigation.findNavController(it)
                        navController.navigate(R.id.action_game_to_resume)
                    }
                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        mAlertDialog.show()
                    } , 1500)
                }
            }

        }


    }
}