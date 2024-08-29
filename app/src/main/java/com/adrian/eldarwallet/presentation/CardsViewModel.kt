package com.adrian.eldarwallet.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.commons.model.Response
import com.adrian.domain.model.request.CreateCardRqDto
import com.adrian.domain.repository.CardsRepository
import com.adrian.eldarwallet.application.AuthenticatedUser
import com.adrian.eldarwallet.presentation.mappers.toUiModel
import com.adrian.eldarwallet.presentation.model.CardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val authenticatedUser: AuthenticatedUser,
    private val cardsRepository: CardsRepository
) : ViewModel() {

    private val _cards: MutableStateFlow<Response<List<CardItem>>>
        = MutableStateFlow(Response.Loading(false))
    val cards: StateFlow<Response<List<CardItem>>> = _cards.asStateFlow()

    var typedCardNumber by mutableStateOf("")
        private set

    var typedCvv by mutableStateOf("")
        private set

    var typedExpirationDate by mutableStateOf("")
        private set

    fun updateTypedCardNumber(typedCardNumber: String) {
        this.typedCardNumber = typedCardNumber
    }

    fun updateTypedCvv(typedCvv: String) {
        this.typedCvv = typedCvv
    }

    fun updateTypedExpirationDate(typedExpirationDate: String) {
        this.typedExpirationDate = typedExpirationDate
    }

    fun fetchCards() = viewModelScope.launch {
        val userId = authenticatedUser.data.id
        cardsRepository.getCardsByUserId(userId).collect { response ->
            when (response) {
                is Response.Success -> {
                    _cards.value = Response.Success(response.data.map { it.toUiModel() })
                }
                is Response.Failure -> {
                    _cards.value = Response.Failure(response.error, response.message)
                }
                is Response.Loading -> {
                    _cards.value = Response.Loading(true)
                }
            }
        }
    }

    fun isCardDataValid(): Boolean {
        return typedCardNumber.isNotBlank()
                && typedCvv.isNotBlank()
                && typedExpirationDate.isNotBlank()
    }

    fun addCard() = viewModelScope.launch {
        cardsRepository.createCard(authenticatedUser.data.id, CreateCardRqDto(
            number = typedCardNumber,
            cvv = typedCvv.toInt(),
            expiry = typedExpirationDate
        )).collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchCards()
                }
                is Response.Failure -> {

                }
                is Response.Loading -> {

                }
            }
        }
    }

    fun deleteCard(cardId: Long) = viewModelScope.launch {
        cardsRepository.deleteCard(cardId).collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchCards()
                }
                else -> { /* Do nothing for the moment */}
            }
        }
    }

    fun isInitialState(): Boolean {
        if (cards.value is Response.Loading) {
            return !(cards.value as Response.Loading).isLoading
        }
        return false
    }
}