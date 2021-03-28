package com.footiestats.service

import com.footiestats.model.FormModel
import com.footiestats.model.MatchResponse
import com.footiestats.model.footiestatsmodel.FootieStatsModel
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface FootieService {

    fun getLeagueTable(): ResponseEntity<FootieStatsModel>

    fun getFormList() : ResponseEntity<List<FormModel>>

    fun getNextFixtures(): ResponseEntity<MatchResponse>

}