package com.adrian.eldarwallet.presentation.mappers

import com.adrian.domain.model.response.AuthUserRsDto
import com.adrian.domain.model.response.UserCredentialsRsDto
import com.adrian.eldarwallet.presentation.model.AuthUser
import com.adrian.eldarwallet.presentation.model.UserCredentials

fun AuthUserRsDto.toUiModel(): AuthUser =
    AuthUser(
        id = this.id,
        name = this.name,
        lastName = this.lastName
    )

fun UserCredentialsRsDto.toUiModel(): UserCredentials =
    UserCredentials(
        username = this.username,
        password = this.password
    )