package serviceTests

import com.footiestats.service.FootieService
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
    fun `Verify that FootieService GetForm returns a List of 5 String elements `() {

        val expectedlist = mutableListOf<String>()

        expectedlist.addAll(listOf("W", "W", "L","W","D"))

        val response = footieService.getFormList(66)

        assertEquals(response.body?.size,5)

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
        assertEquals(actualHeaders.headers.accept.get(0).toString(),"application/json")
        assertEquals(actualHeaders.headers.get("X-Auth-Token").toString(), "[2a88122678894952829ef98dd6e898f6]")
    }

}