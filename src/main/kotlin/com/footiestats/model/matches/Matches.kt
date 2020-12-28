package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName


data class Matches (

        @SerializedName("id") var id : Int,
        @SerializedName("season") var season : Season,
        @SerializedName("utcDate") var utcDate : String,
        @SerializedName("status") var status : String,
        @SerializedName("matchday") var matchday : Int,
        @SerializedName("stage") var stage : String,
        @SerializedName("group") var group : String,
        @SerializedName("lastUpdated") var lastUpdated : String,
        @SerializedName("homeTeam") var homeTeam : HomeTeam,
        @SerializedName("awayTeam") var awayTeam : AwayTeam,

)