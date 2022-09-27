package ru.kit.countryservice.service

import ru.kit.countryservice.dto.CountryDTO

interface CountryService {
    fun getAll(): List<CountryDTO>
}
