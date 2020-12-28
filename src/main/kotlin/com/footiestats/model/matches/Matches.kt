package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName

data class Matches (

        @SerializedName("count") var count : Int,
        @SerializedName("filters") var filters : Filters,
        @SerializedName("matches") var matches : List<Matches>

)