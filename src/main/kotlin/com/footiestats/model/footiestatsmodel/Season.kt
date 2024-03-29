package com.footiestats.model.footiestatsmodel

import com.google.gson.annotations.SerializedName


data class Season (

        @SerializedName("id") var id : Int,
        @SerializedName("startDate") var startDate : String,
        @SerializedName("endDate") var endDate : String,
        @SerializedName("currentMatchday") var currentMatchday : Int,
        @SerializedName("winner") var winner : String?

)