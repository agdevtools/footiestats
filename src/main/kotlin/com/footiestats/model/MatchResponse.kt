package com.footiestats.model

import com.footiestats.model.matches.FixtureDetails
import com.google.gson.annotations.SerializedName

data class MatchResponse (
        @SerializedName("status") var status : Int,
        @SerializedName("fixtureDetails") var fixtureDetails : List<FixtureDetails>
)
