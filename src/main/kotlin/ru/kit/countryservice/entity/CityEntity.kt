package ru.kit.countryservice.entity

import javax.persistence.*

@Entity
@Table(name = "city")
class CityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,
    val name: String = "",

    @ManyToOne
    @JoinColumn(name = "country_id")
    var country: CountryEntity
)