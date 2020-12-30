package com.footiestats.service

import com.footiestats.model.MatchResponse
import com.footiestats.model.footieStatsModel.FootieStatsModel
import com.footiestats.model.matches.MatchDetails
import com.footiestats.model.matches.Matches
import com.footiestats.model.matches.MatchesModel
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


private const val uri = "https://api.football-data.org/v2/competitions/PL/standings"
private const val matchesUri = "https://api.football-data.org/v2/teams/66/matches"
private val restTemplate = RestTemplate()

@Service
class FootieService {
    fun getLeagueTable(): ResponseEntity<FootieStatsModel> {
        val response = makeStandingsRestCall()
        return ResponseEntity(response.body, HttpStatus.OK)
    }

   fun getFormList(teamId: Int) : ResponseEntity<Array<String>> {
        val response = makeStandingsRestCall()
        return ResponseEntity(getLeagueTableList(response,teamId), HttpStatus.OK)
    }

    fun getNextFixture(): ResponseEntity<MatchResponse> {
        val response = makeMatchesRestCall()
        return ResponseEntity(MatchResponse(200, getNextFixtureDetails(response)),HttpStatus.OK)
    }

    private fun makeStandingsRestCall():ResponseEntity<FootieStatsModel> {
        return restTemplate.exchange(uri, HttpMethod.GET, getHeaders(), FootieStatsModel::class.java)
    }

    private fun makeMatchesRestCall():ResponseEntity<MatchesModel> {
        return restTemplate.exchange(matchesUri, HttpMethod.GET, getHeaders(), MatchesModel::class.java)
    }

    fun getHeaders(): HttpEntity<String> {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.set("X-Auth-Token", "2a88122678894952829ef98dd6e898f6")
        return HttpEntity("parameters", headers)
    }

    fun getLeagueTableList(response: ResponseEntity<FootieStatsModel>, teamId: Int):Array<String>? {
        val tables = response.body?.standings?.get(0)?.table
        var formDetails = ""
        if (tables != null) {
            for (table in tables) {
                if (table.team?.id?.equals(teamId)!!) {
                    formDetails = table.form.toString()
                }
            }
        }
        return formDetails.split(",").toTypedArray()
    }

    fun getCurrentMatchDay(response: ResponseEntity<MatchesModel>): Int? {
        return response.body?.matches?.get(0)?.season?.currentMatchday
    }

    fun getNextMatchDay(response: ResponseEntity<MatchesModel>) : Int? {
        return getCurrentMatchDay(response)?.plus(1)
    }

   fun getNextFixtureDetails(response: ResponseEntity<MatchesModel>) : MatchDetails {
            val matches = response.body?.matches
            var matchDetails = MatchDetails()
            if (matches != null) {
                for (match in matches)
                    if (match.matchday.equals(getNextMatchDay(response))) {
                        matchDetails.id = match.id
                        matchDetails.status = match.status
                        matchDetails.utcDate = match.utcDate
                        matchDetails.matchday = match.matchday
                        matchDetails.homeTeam = match.homeTeam.name
                        matchDetails.awayTeam = match.awayTeam.name
                        matchDetails.homeTeamId = match.homeTeam.id
                        matchDetails.awayTeamId = match.awayTeam.id
                    }

            }
        return matchDetails
    }
}