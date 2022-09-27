package ru.kit.countryservice.controller

import org.springframework.web.bind.annotation.*
import ru.kit.countryservice.dto.CountryDTO
import ru.kit.countryservice.service.CountryService

@RestController
@RequestMapping("countries")
class CountryController(
    private val countryService: CountryService
) {
    @GetMapping
    fun getAll(): List<CountryDTO> = countryService.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Int) = countryService.getById(id)

    @PostMapping
    fun create(@RequestBody countryDTO: CountryDTO): Int {
        return countryService.create(countryDTO)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody countryDTO: CountryDTO) {
        countryService.update(id, countryDTO)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Int) {
        countryService.delete(id)
    }
}