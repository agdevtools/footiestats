package com.footiestats.controller

import com.footiestats.model.FormModel
import com.footiestats.model.MatchResponse
import com.footiestats.model.footiestatsmodel.*
import com.footiestats.model.matches.FixtureDetails
import com.footiestats.service.FootieService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import java.util.*


internal class FootieControllerTests{

    private lateinit var footieService : FootieService
    private lateinit var footieController: FootieController
    private var mockMvc: MockMvc? = null


    @BeforeEach
    fun initMocks() {
        footieService = mock()
        footieController = FootieController(footieService)
        MockitoAnnotations.initMocks(this)
        mockMvc = standaloneSetup(FootieController(footieService))
                .build()
    }

    @Test
    fun `Verify that FootieController GetLeague calls Footie Service `() {
        val area = Area(2,"England")
        val season = Season(1,"01/08/2020","01/06/2021",18,"")
        val team1 = Team(1,"Man Utd", "")
        val team2 = Team(2,"Newcastle", "")
        val table1 = Table(1,team1,10,"WWWWW",10,0,0,30,100,0,100)
        val table2 = Table(2,team2,10,"LLLLL",0,0,10,0,0,100,-100)
        val expectedMutableTableList: MutableList<Table> = ArrayList<Table>()
        expectedMutableTableList.add(table1)
        expectedMutableTableList.add(table2)
        val standings1 = Standings("test1","Prem","",expectedMutableTableList)
        val standings2 = Standings("test2","Euro","",expectedMutableTableList)
        val expectedMutableStandingsList: MutableList<Standings> = ArrayList<Standings>()
        expectedMutableStandingsList.add(standings1)
        expectedMutableStandingsList.add(standings2)
        val expectedCompetition = Competition(2021,area, "test","test","test","01/01/2021")
        val expectedFootieStatsModel = FootieStatsModel(expectedCompetition,season,expectedMutableStandingsList)
        val expectedLeagueResponse = ResponseEntity(expectedFootieStatsModel,HttpStatus.OK)

        whenever(footieService.getLeagueTable()).thenReturn(expectedLeagueResponse)

        mockMvc?.perform(MockMvcRequestBuilders.get("/api/league")
                .contentType(MediaType.APPLICATION_JSON))
                ?.andExpect(MockMvcResultMatchers.status().isOk)
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.competition.id", Is.`is`(2021)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.competition.name", Is.`is`("test")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.season.id", Is.`is`(1)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.season.currentMatchday", Is.`is`(18)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[0].stage", Is.`is`("test1")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[1].stage", Is.`is`("test2")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[0].table[0].position", Is.`is`(1)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[0].table[0].won", Is.`is`(10)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[0].table[1].position", Is.`is`(2)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[0].table[1].won", Is.`is`(0)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[0].table[0].team.name", Is.`is`("Man Utd")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.standings[0].table[1].team.name", Is.`is`("Newcastle")))
    }

    @Test
    fun `Test that getNextFixtures returns a correct repsonse with FixtureList `() {
        val expectedFixture1 = FixtureDetails(5, "01-JAN-1980", "SCHEDULED", 17, "Man Utd", "Newcastle", 1, 2)
        val expectedFixture2 = FixtureDetails(6, "01-JAN-1980", "SCHEDULED", 17, "Brighton", "Palace", 3, 4)
        val expectedFixtureDetailsList: MutableList<FixtureDetails> = ArrayList<FixtureDetails>()
        expectedFixtureDetailsList.add(expectedFixture1)
        expectedFixtureDetailsList.add(expectedFixture2)
        val expectedMatchResponse = ResponseEntity(MatchResponse(200, expectedFixtureDetailsList), HttpStatus.OK)

        whenever(footieService.getNextFixtures()).thenReturn(expectedMatchResponse)
                .thenReturn(expectedMatchResponse)

        mockMvc?.perform(MockMvcRequestBuilders.get("/api/next")
                .contentType(MediaType.APPLICATION_JSON))
                ?.andExpect(MockMvcResultMatchers.status().isOk)
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.`is`(200)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[0].homeTeam", Is.`is`("Man Utd")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[0].awayTeam", Is.`is`("Newcastle")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[0].matchday", Is.`is`(17)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[0].status", Is.`is`("SCHEDULED")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[0].utcDate", Is.`is`("01-JAN-1980")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[0].homeTeamId", Is.`is`(1)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[0].awayTeamId", Is.`is`(2)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[1].homeTeam", Is.`is`("Brighton")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$.fixtureDetails[1].awayTeam", Is.`is`("Palace")))
    }

    @Test
    fun `Test that getFormList returns a correct response with FormList `() {
        val expectedFormModelMutableList : MutableList<FormModel> = ArrayList<FormModel>()
        val expectedFormList1: MutableList<String> = ArrayList<String>()
        expectedFormList1.add("W")
        expectedFormList1.add("W")
        expectedFormList1.add("W")
        expectedFormList1.add("W")
        expectedFormList1.add("W")
        val expectedFormList2: MutableList<String> = ArrayList<String>()
        expectedFormList2.add("L")
        expectedFormList2.add("D")
        expectedFormList2.add("W")
        expectedFormList2.add("W")
        expectedFormList2.add("W")
        val expectedFormModel1 = FormModel(5, expectedFormList1)
        val expectedFormModel2 = FormModel(6, expectedFormList2)
        expectedFormModelMutableList.add(expectedFormModel1)
        expectedFormModelMutableList.add(expectedFormModel2)
        val expectedFormModelList : List<FormModel> = expectedFormModelMutableList

        val expectedFormResponse = ResponseEntity(expectedFormModelList, HttpStatus.OK)

        whenever(footieService.getFormList()).thenReturn(expectedFormResponse)

       mockMvc?.perform(MockMvcRequestBuilders.get("/api/form")
                .contentType(MediaType.APPLICATION_JSON))
                ?.andExpect(MockMvcResultMatchers.status().isOk)
                ?.andExpect(MockMvcResultMatchers.jsonPath("$[0].teamId", Is.`is`(5)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$[1].teamId", Is.`is`(6)))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$[0].form[0]", Is.`is`("W")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$[0].form[1]", Is.`is`("W")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$[1].form[0]", Is.`is`("L")))
                ?.andExpect(MockMvcResultMatchers.jsonPath("$[1].form[1]", Is.`is`("D")))

    }
}