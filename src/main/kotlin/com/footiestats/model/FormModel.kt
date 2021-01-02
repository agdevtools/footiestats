package com.footiestats.model

import com.google.gson.annotations.SerializedName

data class FormModel (
        @SerializedName("teamId") var teamId : Int = 0,
        @SerializedName("form") var form : List<String>
)
