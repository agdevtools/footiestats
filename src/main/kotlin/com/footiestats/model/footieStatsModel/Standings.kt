package com.footiestats.model.footieStatsModel

import com.google.gson.annotations.SerializedName


data class Standings (

        @SerializedName("stage") var stage : String,
        @SerializedName("type") var type : String,
        @SerializedName("group") var group : String?,
        @SerializedName("table") var table : List<Table>

)