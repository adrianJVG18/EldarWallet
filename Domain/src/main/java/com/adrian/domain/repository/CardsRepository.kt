package com.adrian.domain.repository

import com.adrian.commons.model.Response
import com.adrian.domain.model.request.CreateCardRqDto
import com.adrian.domain.model.response.CardRsDto
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    fun getCardsByUserId(userId: Long) : Flow<Response<List<CardRsDto>>>
    fun createCard(userId: Long, card: CreateCardRqDto): Flow<Response<Long>>
    fun deleteCard(cardId: Long) : Flow<Response<Long>>
}