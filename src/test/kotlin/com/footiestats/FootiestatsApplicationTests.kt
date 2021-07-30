package com.footiestats

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FootiestatsApplicationTests {

	lateinit var footiestatsApplication: FootiestatsApplication

	@Test
	fun contextLoads() {
	}

	@Test
	fun test_setOperatingSystem_thenSetsCorrectProfile() {
		setOperatingSystem()
		if (getOperatingSystem().toLowerCase().contains("mac")) {
			Assertions.assertEquals("local", System.getProperty("spring.profiles.active"))
		} else {
			Assertions.assertEquals("prod", System.getProperty("spring.profiles.active"))
		}
	}

}
