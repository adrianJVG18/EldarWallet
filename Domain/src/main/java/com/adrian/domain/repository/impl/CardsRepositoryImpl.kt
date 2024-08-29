package com.adrian.domain.repository.impl

import android.util.Log
import com.adrian.commons.model.Response
import com.adrian.data.dao.CardsDao
import com.adrian.data.entity.Card
import com.adrian.domain.encryption.CryptoGraph
import com.adrian.domain.model.request.CreateCardRqDto
import com.adrian.domain.model.response.CardRsDto
import com.adrian.domain.repository.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CardsRepositoryImpl @Inject constructor(
    private val cardsDao: CardsDao,
    private val cryptoGraph: CryptoGraph
) : CardsRepository{
    override fun getCardsByUserId(userId: Long): Flow<Response<List<CardRsDto>>> = flow {
        emit(Response.Loading(true))

    }

    override fun createCard(userId: Long, card: CreateCardRqDto): Flow<Response<Long>> = flow {
        emit(Response.Loading(true))

        Log.d("CardsRepositoryImpl", "Creating Card, starting to encrypt: ")

        val cardEntity = Card(
            userId = cryptoGraph.encrypt("$userId"),
            number = cryptoGraph.encrypt(card.number),
            cvv = cryptoGraph.encrypt(card.cvv.toString()),
            expiry = cryptoGraph.encrypt(card.expiry)
        )

        Log.d("CardsRepositoryImpl", "Encrypted data: userId: $userId (${cardEntity.userId})")

        val inserted = cardsDao.insertCard(cardEntity)
        emit(Response.Success(inserted))
    }.catch {
        emit(Response.Failure(it as Exception, "Failed to Insert the Card"))
    }.flowOn(Dispatchers.IO)

    override fun deleteCard(cardId: Long): Flow<Response<Long>> = flow {
        emit(Response.Loading(true))

        cardsDao.deleteCardById(cardId)
        emit(Response.Success(cardId))
    }.catch {
        emit(Response.Failure(it as Exception, "Failed to Delete the Card with id {${cardId}}"))
    }.flowOn(Dispatchers.IO)
}