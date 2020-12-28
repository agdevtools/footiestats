package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName


data class Filters (

        @SerializedName("permission") var permission : String,
        @SerializedName("limit") var limit : Int

)