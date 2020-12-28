package com.footiestats.controller

import com.footiestats.model.FootieStatsModel
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
    fun getForm(): ResponseEntity<Array<String>> = footieService.getFormList()

}