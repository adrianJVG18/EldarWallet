package com.adrian.eldarwallet.presentation.mappers

import com.adrian.domain.model.response.AuthUserRsDto
import com.adrian.eldarwallet.presentation.model.AuthUser

fun AuthUserRsDto.toUiModel(): AuthUser =
    AuthUser(
        id = this.id,
        name = this.name
    )