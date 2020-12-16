package com.trallerd.quiz.models.game.end

import com.google.gson.annotations.SerializedName

class EndGame(
        var status : String ,
        @SerializedName("started_at") var startedAt : String ,
        @SerializedName("finished_at") var finishedAt : String ,
        var score : Int

) {
}