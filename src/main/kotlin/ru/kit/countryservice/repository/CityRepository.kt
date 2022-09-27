package ru.kit.countryservice.repository

import org.springframework.data.repository.CrudRepository
import ru.kit.countryservice.entity.CityEntity
import ru.kit.countryservice.entity.CountryEntity

interface CityRepository : CrudRepository<CityEntity, Int> {
    fun deleteAllByCountry(country: CountryEntity)
}