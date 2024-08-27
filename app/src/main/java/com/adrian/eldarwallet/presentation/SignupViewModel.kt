package com.adrian.eldarwallet.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.commons.model.Response
import com.adrian.domain.model.request.SignupUserRqDto
import com.adrian.domain.repository.AuthRepository
import com.adrian.eldarwallet.presentation.mappers.toUiModel
import com.adrian.eldarwallet.presentation.model.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _signUpState: MutableStateFlow<Response<AuthUser>> =
        MutableStateFlow(Response.Loading(false))
    val signUpState: StateFlow<Response<AuthUser>> = _signUpState.asStateFlow()

    var typedUsername by mutableStateOf("")
        private set

    var typedPassword by mutableStateOf("")
        private set

    var typedName by mutableStateOf("")
        private set

    var typedLastName by mutableStateOf("")
        private set

    fun updateTypedUsername(typedUsername: String) {
        this.typedUsername = typedUsername
    }

    fun updateTypedPassword(typedPassword: String) {
        this.typedPassword = typedPassword
    }

    fun updateTypedName(typedName: String) {
        this.typedName = typedName
    }

    fun updateTypedLastName(typedLastName: String) {
        this.typedLastName = typedLastName
    }

    fun attemptSigningUp() = viewModelScope.launch {
        authRepository.registerUser(SignupUserRqDto(
            username = typedUsername,
            password = typedPassword,
            name = typedName,
            lastName = typedLastName
        )).collect { response ->
            when (response) {
                is Response.Success -> {
                    _signUpState.value = Response.Success(response.data.toUiModel())
                    authRepository.persisAuthenticatedUser(
                        username = typedUsername,
                        password = typedPassword
                    )
                }
                is Response.Failure -> {
                    _signUpState.value = Response.Failure(response.error, response.message)
                }
                is Response.Loading -> {
                    _signUpState.value = Response.Loading(response.isLoading)
                }
            }
        }
    }

    fun validateSignUpConditions(): Boolean {
        return typedName.isNotBlank()
                && typedLastName.isNotBlank()
                && typedUsername.isNotBlank()
                && typedPassword.isNotBlank()
    }

}