package ru.kit.countryservice.service

import ru.kit.countryservice.dto.CountryDTO

interface CountryService {
    fun getAll(): List<CountryDTO>
    fun getById(id: Int): CountryDTO
    fun create(countryDTO: CountryDTO): Int
    fun update(id: Int, countryDTO: CountryDTO)
    fun delete(id: Int)
}
