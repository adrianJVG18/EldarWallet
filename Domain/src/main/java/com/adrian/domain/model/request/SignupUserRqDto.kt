package com.adrian.domain.model.request

data class SignupUserRqDto(
    val username: String,
    val password: String,
    val name: String,
    val lastName: String
)