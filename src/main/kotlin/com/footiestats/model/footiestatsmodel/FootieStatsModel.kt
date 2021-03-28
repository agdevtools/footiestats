package com.footiestats.model.footiestatsmodel

import com.google.gson.annotations.SerializedName


data class FootieStatsModel (

        @SerializedName("competition") var competition : Competition,
        @SerializedName("season") var season : Season,
        @SerializedName("standings") var standings : List<Standings>

)