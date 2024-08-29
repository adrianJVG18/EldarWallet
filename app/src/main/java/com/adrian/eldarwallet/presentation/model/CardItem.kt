package com.adrian.eldarwallet.presentation.model

data class CardItem(
    val id: Long,
    val number: String,
    val cvv: Int?,
    val expiry: String
)