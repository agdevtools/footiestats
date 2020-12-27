package com.footiestats.ControllerTests

import com.footiestats.controller.FootieController
import com.footiestats.service.FootieService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.http.ResponseEntity

internal class FootieControllerTests{

    private lateinit var footieService : FootieService
    private lateinit var footieController: FootieController

    @BeforeEach
    fun setUp() {
        footieService = mock()
        footieController = FootieController(footieService)
    }

    @Test
    fun `Verify that FootieController GetLeague calls Footie Service `() {

        footieController.getLeagueTable()
        verify(footieService,times(1)).getLeagueTable()

    }

    @Test
    fun `Verify that FootieController GetForm calls Footie Service `() {

        val expectedList = arrayOf("W", "W", "L","W","D")

        whenever(footieService.getForm()).thenReturn(ResponseEntity.ok(expectedList))

        val actualList = footieController.getForm()

        verify(footieService,times(1)).getForm()
        assertEquals(actualList.body?.size, 5)
        assertEquals(actualList.body?.get(0),"W")
        assertEquals(actualList.body?.get(4),"D")
    }
}