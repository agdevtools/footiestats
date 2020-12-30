package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName


data class MatchDetails (

        @SerializedName("id") var id : Int = 0,
        @SerializedName("utcDate") var utcDate : String = "",
        @SerializedName("status") var status : String = "",
        @SerializedName("matchday") var matchday : Int = 0,
        @SerializedName("homeTeam") var homeTeam : String = "",
        @SerializedName("awayTeam") var awayTeam : String = ""
)
