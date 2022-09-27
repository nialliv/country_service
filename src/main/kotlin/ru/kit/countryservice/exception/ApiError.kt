package ru.kit.countryservice.exception

data class ApiError(
    val errorCode: String,
    val description: String,
)