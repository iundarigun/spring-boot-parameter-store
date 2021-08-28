package br.com.devcave.parameterstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParameterStoreApplication

fun main(args: Array<String>) {
	runApplication<ParameterStoreApplication>(*args)
}
