package com.footiestats.model

import com.footiestats.model.matches.MatchDetails
import com.google.gson.annotations.SerializedName

data class MatchResponse (
        @SerializedName("permission") var status : Int,
        @SerializedName("matchDetails") var matchDetails : MatchDetails
)
