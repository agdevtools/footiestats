package com.footiestats.service

import com.footiestats.model.footieStatsModel.FootieStatsModel
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

   fun getFormList(): ResponseEntity<Array<String>> {
        val response = makeStandingsRestCall()
        return ResponseEntity(getLeagueTableList(response), HttpStatus.OK)
    }

    fun getNextFixture(): ResponseEntity<String> {
        val response = makeMatchesRestCall()
        return ResponseEntity(getNextFixtureDetails(response),HttpStatus.OK)
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

    fun getLeagueTableList(response: ResponseEntity<FootieStatsModel>):Array<String>? {
        val leagueTable = response.body?.standings?.get(0)?.table?.get(2)?.form
        return leagueTable?.split(",")?.toTypedArray()
    }

    private fun getCurrentMatchDay(response: ResponseEntity<MatchesModel>): Int? {
        return response.body?.matches?.get(0)?.season?.currentMatchday
    }

    private fun getNextMatchDay(response: ResponseEntity<MatchesModel>) : Int? {
        return getCurrentMatchDay(response)?.plus(1)
    }

    private fun getNextFixtureDetails(response: ResponseEntity<MatchesModel>) : String {
            val matches = response.body?.matches
            var matchDetails = ""
            if (matches != null) {
                for (match in matches)
                    if (match.matchday.equals(getNextMatchDay(response))) {
                        matchDetails = match.homeTeam.name +" "+"vs"+" "+match.awayTeam.name
                    }

            }
        return matchDetails
    }
}