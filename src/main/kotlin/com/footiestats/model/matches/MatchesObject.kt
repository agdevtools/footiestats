package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName


data class MatchesObject (

        @SerializedName("id") var id : Int = 0,
        @SerializedName("season") var season : Season? = Season(),
        @SerializedName("utcDate") var utcDate : String = "",
        @SerializedName("status") var status : String = "",
        @SerializedName("matchday") var matchday : Int = 0,
        @SerializedName("stage") var stage : String = "",
        @SerializedName("group") var group : String? = "",
        @SerializedName("lastUpdated") var lastUpdated : String = "",
        @SerializedName("homeTeam") var homeTeam : HomeTeam = HomeTeam(),
        @SerializedName("awayTeam") var awayTeam : AwayTeam = AwayTeam()
        )