package ru.kit.countryservice.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kit.countryservice.dto.CityDTO
import ru.kit.countryservice.dto.CountryDTO
import ru.kit.countryservice.entity.CityEntity
import ru.kit.countryservice.entity.CountryEntity
import ru.kit.countryservice.exception.CountryNotFoundException
import ru.kit.countryservice.repository.CityRepository
import ru.kit.countryservice.repository.CountryRepository
import ru.kit.countryservice.service.CountryService

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
    private val cityRepository: CityRepository
) : CountryService {
    override fun getAll(): List<CountryDTO> = countryRepository.findAll().map { it.toDTO() }

    override fun getById(id: Int): CountryDTO =
        countryRepository.findByIdOrNull(id)?.toDTO() ?: throw CountryNotFoundException(id)

    @Transactional
    override fun create(countryDTO: CountryDTO): Int {
        val countryEntity = countryRepository.save(countryDTO.toEntity())
        val citiesEntity = countryDTO.cities.map { it.toEntity(countryEntity) }
        cityRepository.saveAll(citiesEntity)
        return countryEntity.id
    }

    @Transactional
    override fun update(id: Int, countryDTO: CountryDTO) {
        var countryNew = countryRepository.findByIdOrNull(id) ?: throw CountryNotFoundException(id)
        countryNew.name = countryDTO.name
        countryNew.population = countryDTO.population
        countryNew = countryRepository.save(countryNew)
        val cities = countryDTO.cities.map { it.toEntity(countryNew) }
        cityRepository.deleteAllByCountry(countryNew)
        cityRepository.saveAll(cities)

    }

    @Transactional
    override fun delete(id: Int) {
        val country = countryRepository.findByIdOrNull(id) ?: throw CountryNotFoundException(id)
        cityRepository.deleteAllByCountry(country)
        countryRepository.deleteById(country.id)
    }

    private fun CountryEntity.toDTO(): CountryDTO = CountryDTO(
        id = this.id,
        name = this.name,
        population = this.population,
        cities = this.cities.map { it.toDTO() })

    private fun CityEntity.toDTO(): CityDTO = CityDTO(
        name = this.name,
    )

    private fun CityDTO.toEntity(country: CountryEntity): CityEntity = CityEntity(
        id = 0,
        name = this.name,
        country = country
    )

    private fun CountryDTO.toEntity(): CountryEntity = CountryEntity(
        id = 0, name = this.name, population = this.population
    )
}