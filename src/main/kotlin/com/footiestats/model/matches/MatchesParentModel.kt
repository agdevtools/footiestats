package com.footiestats.model.matches

import com.google.gson.annotations.SerializedName

data class MatchesParentModel (

        @SerializedName("count") var count : Int,
        @SerializedName("matches") var matches : List<MatchesObject>?

)