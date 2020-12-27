package serviceTests

import com.footiestats.service.FootieService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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

        val response = footieService.getForm()

        assertEquals(response.body?.size,5)

    }

    @Test
    fun `Verify that FootieService GetLeague returns a valid body`() {

        val response = footieService.getLeagueTable()

        response?.body?.isNotEmpty()?.let { assert(it) }

    }

}