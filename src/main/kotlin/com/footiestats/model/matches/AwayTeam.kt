package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName

data class AwayTeam (

        @SerializedName("id") var id : Int,
        @SerializedName("name") var name : String

)