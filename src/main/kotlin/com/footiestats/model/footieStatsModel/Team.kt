package com.footiestats.model.footieStatsModel

import com.google.gson.annotations.SerializedName


data class Team (

        @SerializedName("id") var id : Int = 0,
        @SerializedName("name") var name : String = "",
        @SerializedName("crestUrl") var crestUrl : String = ""

)