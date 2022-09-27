package ru.kit.countryservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CountryServiceApplication

fun main(args: Array<String>) {
    runApplication<CountryServiceApplication>(*args)
}
