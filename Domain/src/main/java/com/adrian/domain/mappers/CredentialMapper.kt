package com.adrian.domain.mappers

import com.adrian.data.entity.Credential
import com.adrian.domain.model.request.SignupUserRqDto

fun SignupUserRqDto.toCredential(userId: Long): Credential {
    return Credential(
        userId = userId,
        username = this.username,
        password = this.password
    )
}