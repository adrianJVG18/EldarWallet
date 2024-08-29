package com.adrian.eldarwallet.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.commons.model.Response
import com.adrian.domain.model.request.SignInRqDto
import com.adrian.domain.repository.AuthRepository
import com.adrian.eldarwallet.application.AuthenticatedUser
import com.adrian.eldarwallet.presentation.mappers.toUiModel
import com.adrian.eldarwallet.presentation.model.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authenticatedUser: AuthenticatedUser
) : ViewModel() {

    private val _signInState: MutableStateFlow<Response<AuthUser>> =
        MutableStateFlow(Response.Loading(false))
    val signInState: StateFlow<Response<AuthUser>> = _signInState.asStateFlow()

    var typedUsername by mutableStateOf("")
        private set

    var typedPassword by mutableStateOf("")
        private set

    fun updateTypedUsername(typedUsername: String) {
        this.typedUsername = typedUsername
    }

    fun updateTypedPassword(typedPassword: String) {
        this.typedPassword = typedPassword
    }

    fun attemptLogin() = viewModelScope.launch {
        authRepository.authUser(SignInRqDto(
            username = typedUsername,
            password = typedPassword
        )).collect { response ->
            when (response) {
                is Response.Success -> {
                    response.data.toUiModel().let {
                        _signInState.value = Response.Success(it)
                        authenticatedUser.data = it
                    }
                }
                is Response.Failure -> {
                    _signInState.value = Response.Failure(response.error, response.message)
                }
                is Response.Loading -> {
                    _signInState.value = Response.Loading(response.isLoading)
                }
            }
        }
    }
}