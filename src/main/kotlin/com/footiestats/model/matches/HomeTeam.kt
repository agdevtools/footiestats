package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName


data class HomeTeam (

        @SerializedName("id") var id : Int = 0,
        @SerializedName("name") var name : String = ""

)