package com.trallerd.quiz.models.game.start

import com.google.gson.annotations.SerializedName

class Game(
        var creation : String ,
        var status : String ,
        @SerializedName("started_at") var startedAt : String ,
        var score : Int
) {
}