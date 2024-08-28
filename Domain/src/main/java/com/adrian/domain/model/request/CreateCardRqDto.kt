package com.adrian.domain.model.request

data class CreateCardRqDto(
    val number: String,
    val cvv: Int?,
    val expiry: String
)