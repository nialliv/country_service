package ru.kit.countryservice.repository

import org.springframework.data.repository.CrudRepository
import ru.kit.countryservice.entity.CountryEntity

interface CountryRepository : CrudRepository<CountryEntity, Int>