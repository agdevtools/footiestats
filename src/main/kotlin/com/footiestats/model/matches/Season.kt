package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName


data class Season (

        @SerializedName("id") var id : Int = 0,
        @SerializedName("startDate") var startDate : String = "",
        @SerializedName("endDate") var endDate : String = "",
        @SerializedName("currentMatchday") var currentMatchday : Int = 0,
        @SerializedName("winner") var winner : String? = ""

)