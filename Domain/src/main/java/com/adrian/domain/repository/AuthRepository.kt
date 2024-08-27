package com.adrian.domain.repository

import com.adrian.commons.model.Response
import com.adrian.domain.model.response.AuthUserRsDto
import com.adrian.domain.model.request.SignupUserRqDto
import com.adrian.domain.model.request.SignInRqDto
import com.adrian.domain.model.response.UserCredentialsRsDto
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun authUser(credentials: SignInRqDto): Flow<Response<AuthUserRsDto>>
    fun registerUser(request: SignupUserRqDto): Flow<Response<AuthUserRsDto>>
}