package com.footiestats.controller

import com.footiestats.service.FootieService
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*


@Configuration
@RestController
@RequestMapping("/footiestats")
class FootieController(private val footieService: FootieService) {

    @RequestMapping(value = ["/league"], produces = [MediaType.APPLICATION_JSON_VALUE], method = [RequestMethod.GET])
    fun getLeagueTable(): String? {
        val responseEntity: ResponseEntity<String?>? = footieService.getLeagueTable()
        return Objects.requireNonNull(responseEntity?.body)
    }
}