package com.adrian.domain.repository.impl

import com.adrian.commons.model.Response
import com.adrian.data.dao.CardsDao
import com.adrian.data.entity.Card
import com.adrian.domain.encryption.CryptoGraph
import com.adrian.domain.errors.EncryptionError
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
    override fun getCardsByUserId(userId: Long): Flow<Response<List<CardRsDto>>> = flow<Response<List<CardRsDto>>> {
        emit(Response.Loading(true))

        val cards = cardsDao.getCardsByUserId(cryptoGraph.encrypt("$userId"))
            .map { CardRsDto(
                id = it.id ?: throw EncryptionError(),
                number = cryptoGraph.decrypt(it.number ?: "" ),
                cvv = cryptoGraph.decrypt(it.cvv ?: "0").toInt(),
                expiry = cryptoGraph.decrypt(it.expiry ?: "")
            ) }
        emit(Response.Success(cards))
    }.catch {
        emit(Response.Failure(it as Exception, it.message ?: "Failed to get Cards associated to user $userId"))
    }.flowOn(Dispatchers.IO)

    override fun createCard(userId: Long, card: CreateCardRqDto): Flow<Response<Long>> = flow {
        emit(Response.Loading(true))

        val cardEntity = Card(
            userId = cryptoGraph.encrypt("$userId"),
            number = cryptoGraph.encrypt(card.number),
            cvv = cryptoGraph.encrypt(card.cvv.toString()),
            expiry = cryptoGraph.encrypt(card.expiry)
        )

        if (cardEntity.userId.isNullOrBlank()) {
            emit(Response.Failure(Exception(), "Failed to encrypt the Card"))
        } else {
            val inserted = cardsDao.insertCard(cardEntity)
            emit(Response.Success(inserted))
        }
    }.catch {
        emit(Response.Failure(it as Exception, "Failed to Insert the Card"))
    }.flowOn(Dispatchers.IO)

    override fun deleteCard(cardId: Long): Flow<Response<Long>> = flow {
        emit(Response.Loading(true))

        cardsDao.deleteCardById(cryptoGraph.encrypt("$cardId"))
        emit(Response.Success(cardId))
    }.catch {
        emit(Response.Failure(it as Exception, "Failed to Delete the Card with id {${cardId}}"))
    }.flowOn(Dispatchers.IO)
}