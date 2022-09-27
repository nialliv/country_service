package ru.kit.countryservice.service.impl

import org.springframework.data.repository.findByIdOrNull
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

    override fun getById(id: Int): CountryDTO = countryRepository.findByIdOrNull(id)
        ?.toDTO()
        ?: throw RuntimeException("Country not found")

    override fun create(countryDTO: CountryDTO): Int {
        return countryRepository.save(countryDTO.toEntity()).id
    }

    override fun update(id: Int, countryDTO: CountryDTO) {
        val countryNew = countryRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Country not found")
        countryNew.name = countryDTO.name
        countryNew.population = countryDTO.population
        countryRepository.save(countryNew)
    }

    override fun delete(id: Int) {
        val country = countryRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Country not found")

        countryRepository.deleteById(country.id)

    }

    private fun CountryEntity.toDTO(): CountryDTO =
        CountryDTO(
            id = this.id,
            name = this.name,
            population = this.population
        )

    private fun CountryDTO.toEntity(): CountryEntity =
        CountryEntity(
            id = 0,
            name = this.name,
            population = this.population
        )
}