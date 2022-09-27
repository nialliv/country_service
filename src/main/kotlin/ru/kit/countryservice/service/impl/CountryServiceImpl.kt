package ru.kit.countryservice.service.impl

import org.springframework.stereotype.Service
import ru.kit.countryservice.dto.CountryDTO
import ru.kit.countryservice.entity.CountryEntity
import ru.kit.countryservice.repository.CountryRepository
import ru.kit.countryservice.service.CountryService

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository
) : CountryService {
    override fun getAll(): List<CountryDTO> = countryRepository.findAll().map { it.toDTO() }

    private fun CountryEntity.toDTO(): CountryDTO =
        CountryDTO(
            id = this.id,
            name = this.name,
            population = this.population
        )

}