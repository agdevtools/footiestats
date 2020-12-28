package com.footiestats.service

import com.footiestats.model.FootieStatsModel
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


val uri = "https://api.football-data.org/v2/competitions/PL/standings"
val restTemplate = RestTemplate()
val headers = HttpHeaders()

@Service
class FootieService {
    fun getLeagueTable(): ResponseEntity<FootieStatsModel> {

        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.set("X-Auth-Token", "2a88122678894952829ef98dd6e898f6")
        val entity = HttpEntity("parameters", headers)

        val response = restTemplate.exchange(uri, HttpMethod.GET, entity, FootieStatsModel::class.java)
        return ResponseEntity(response.body, HttpStatus.OK)
    }

   fun getForm(): ResponseEntity<Array<String>> {

        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.set("X-Auth-Token", "2a88122678894952829ef98dd6e898f6")
        val entity = HttpEntity("parameters", headers)

        val response = restTemplate.exchange(uri, HttpMethod.GET, entity, FootieStatsModel::class.java)
        val standings = response.body?.standings
        val table = standings?.get(0)?.table?.get(2)?.form


        val list = table?.split(",")?.toTypedArray()

        return ResponseEntity(list, HttpStatus.OK)
    }
}