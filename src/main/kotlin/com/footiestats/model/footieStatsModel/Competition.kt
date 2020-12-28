package com.footiestats.model.footieStatsModel

import com.footiestats.model.footieStatsModel.Area
import com.google.gson.annotations.SerializedName


data class Competition (

        @SerializedName("id") var id : Int,
        @SerializedName("area") var area : Area,
        @SerializedName("name") var name : String,
        @SerializedName("code") var code : String,
        @SerializedName("plan") var plan : String,
        @SerializedName("lastUpdated") var lastUpdated : String

)