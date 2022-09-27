package ru.kit.countryservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kit.countryservice.dto.CountryDTO
import ru.kit.countryservice.service.CountryService

@RestController
@RequestMapping("countries")
class CountryController (
    private val countryService: CountryService
        ){
    @GetMapping
    fun getAll(): List<CountryDTO> = countryService.getAll()
}