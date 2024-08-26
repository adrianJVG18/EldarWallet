package com.adrian.eldarwallet.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.commons.model.Response
import com.adrian.eldarwallet.presentation.model.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _signInState: MutableStateFlow<Response<AuthUser>> =
        MutableStateFlow(Response.Loading(false))

    val signInState: StateFlow<Response<AuthUser>> = _signInState.asStateFlow()

    var typedUsername by mutableStateOf("")
        private set

    var typedPassword by mutableStateOf("")
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    fun updateTypedUsername(typedUsername: String) {
        this.typedUsername = typedUsername
    }

    fun updateTypedPassword(typedPassword: String) {
        this.typedPassword = typedPassword
    }

    fun attemptLogin() {
        viewModelScope.launch {
            // TODO complete implementation
            isLoading = true

            // TODO remove when implemented
            delay(1000)
            isLoading = false
        }
    }
}