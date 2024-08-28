package com.adrian.eldarwallet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.commons.model.Response
import com.adrian.eldarwallet.presentation.model.CardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(

) : ViewModel() {

    private val _cards: MutableStateFlow<Response<List<CardItem>>>
        = MutableStateFlow(Response.Loading(false))
    val cards: StateFlow<Response<List<CardItem>>> = _cards.asStateFlow()

    fun fetchCards() = viewModelScope.launch {

    }

}