package com.footiestats.model.footieStatsModel

import com.google.gson.annotations.SerializedName


data class Table (
        @SerializedName("position") var position : Int? = 0,
        @SerializedName("team") var team : Team? = Team(),
        @SerializedName("playedGames") var playedGames : Int?  = 0,
        @SerializedName("form") var form : String? = "",
        @SerializedName("won") var won : Int? = 0,
        @SerializedName("draw") var draw : Int? = 0,
        @SerializedName("lost") var lost : Int? = 0,
        @SerializedName("points") var points : Int? = 0,
        @SerializedName("goalsFor") var goalsFor : Int? = 0,
        @SerializedName("goalsAgainst") var goalsAgainst : Int? = 0,
        @SerializedName("goalDifference") var goalDifference : Int? = 0
)