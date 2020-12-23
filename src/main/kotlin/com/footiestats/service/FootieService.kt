package com.footiestats.service

import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class FootieService {
    fun getLeagueTable(): ResponseEntity<String?>? {
        val uri = "https://api.football-data.org/v2/competitions/PL/standings"
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.add("X-Auth-Token", "2a88122678894952829ef98dd6e898f6")
        val entity = HttpEntity("parameters", headers)
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String::class.java)
    }
}