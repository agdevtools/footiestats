package serviceTests

import com.footiestats.model.FormModel
import com.footiestats.model.MatchResponse
import com.footiestats.model.matches.FixtureDetails
import com.footiestats.service.FootieService
import com.nhaarman.mockitokotlin2.isA
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.isA
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

internal class FootieServiceTests{

    private lateinit var footieService : FootieService

    @BeforeEach
    fun setUp() {
        footieService = FootieService()
    }

    @Test
    fun `Verify that FootieService GetForm returns a List of 20 teams Form Model Data Types `() {

        val response = footieService.getFormList()

        assertEquals(response.body?.size,20)
        assertEquals(response.body?.get(0)?.form?.size,5)
        assert(response.body is List<FormModel>)

    }

    @Test
    fun `Verify that FootieService GetNextFixtures returns a MatchResponse with a List of 5 FixtureDetails Data Types `() {

        val response = footieService.getNextFixtures()

        assert(response.body is MatchResponse)
        assertEquals(response.body?.fixtureDetails?.size,5)
        assert(response.body?.fixtureDetails is List<FixtureDetails>)
    }

    @Test
    fun `Verify that FootieService GetLeague returns a valid body`() {

        val response = footieService.getLeagueTable()

        assert(response.hasBody())

    }

    @Test
    fun `Verify that getHeaders returns correct values`() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.set("X-Auth-Token", "2a88122678894952829ef98dd6e898f6")
        val expectedHttpEntity = HttpEntity("parameters", headers)

        val actualHeaders = footieService.getHeaders()

        assertEquals(actualHeaders.body,expectedHttpEntity.body)
        assertEquals(actualHeaders.headers.size, 2)
        assertEquals(actualHeaders.headers.accept[0].toString(),"application/json")
        assertEquals(actualHeaders.headers.get("X-Auth-Token").toString(), "[2a88122678894952829ef98dd6e898f6]")
    }

}