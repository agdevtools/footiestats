package com.footiestats.controller

import com.footiestats.model.FormModel
import com.footiestats.model.MatchResponse
import com.footiestats.model.footieStatsModel.FootieStatsModel
import com.footiestats.service.FootieService
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Configuration
@RestController
@RequestMapping("/api")
class FootieController(private val footieService: FootieService) {

    @RequestMapping(value = ["/league"])
    fun getLeagueTable(): ResponseEntity<FootieStatsModel> = footieService.getLeagueTable()

    @GetMapping(value = ["/form"])
    fun getForm(): ResponseEntity<kotlin.collections.List<FormModel>> = footieService.getFormList()

    @GetMapping(value = ["/next"])
    fun getNextFixtures(): ResponseEntity<MatchResponse> = footieService.getNextFixtures()

}