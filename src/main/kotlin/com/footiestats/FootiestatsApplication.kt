package com.footiestats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FootiestatsApplication

fun main(args: Array<String>) {
	runApplication<FootiestatsApplication>(*args)
	print("Running Footie Stats...")
}
