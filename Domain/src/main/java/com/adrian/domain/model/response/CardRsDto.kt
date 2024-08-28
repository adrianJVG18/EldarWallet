package com.adrian.domain.model.response

data class CardRsDto(
    val id: Long,
    val number: String,
    val cvv: Int?,
    val expiry: String
)