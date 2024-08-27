package com.adrian.domain.mappers

import com.adrian.data.entity.User
import com.adrian.domain.model.request.SignupUserRqDto
import com.adrian.domain.model.response.AuthUserRsDto

fun SignupUserRqDto.toUser(): User {
    return User(
        name = this.name,
        lastName = this.lastName
    )
}

fun User.toDto(): AuthUserRsDto {
    return AuthUserRsDto(
        id = this.id ?: -1,
        name = this.name ?: "",
        lastName = this.lastName ?: ""
    )
}