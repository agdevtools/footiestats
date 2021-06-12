package com.footiestats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FootiestatsApplication

fun main(args: Array<String>) {
	setOperatingSystem()
	runApplication<FootiestatsApplication>(*args)
	print("Running Footie Stats...")
}


fun getOperatingSystem(): String {
	val os = System.getProperty("os.name")
	println("Using System Property: $os")
	return os
}

fun setOperatingSystem() {
	if (getOperatingSystem().toLowerCase().contains("mac")) {
		System.setProperty("spring.profiles.active", "local")
	} else {
		System.setProperty("spring.profiles.active", "prod")
	}
}