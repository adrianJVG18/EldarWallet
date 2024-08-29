package com.adrian.eldarwallet.presentation.mappers

import com.adrian.domain.model.response.CardRsDto
import com.adrian.eldarwallet.presentation.model.CardItem

fun CardRsDto.toUiModel(): CardItem =
    CardItem(
        id = this.id,
        number = this.number,
        cvv = this.cvv,
        expiry = this.expiry
    )