package com.footiestats.service

import com.footiestats.model.FootieStatsModel
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


private const val uri = "https://api.football-data.org/v2/competitions/PL/standings"
private val restTemplate = RestTemplate()

@Service
class FootieService {
    fun getLeagueTable(): ResponseEntity<FootieStatsModel> {
        val response = makeRestCall()
        return ResponseEntity(response.body, HttpStatus.OK)
    }

   fun getFormList(): ResponseEntity<Array<String>> {
        val response = makeRestCall()
        return ResponseEntity(getLeagueTableList(response), HttpStatus.OK)
    }

    private fun makeRestCall():ResponseEntity<FootieStatsModel> {
        return restTemplate.exchange(uri, HttpMethod.GET, getHeaders(), FootieStatsModel::class.java)
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
}