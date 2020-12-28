package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName

data class MatchesModel (

        @SerializedName("count") var count : Int,
        @SerializedName("matches") var matches : List<Matches>?

)