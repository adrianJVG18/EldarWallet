package com.adrian.domain.repository.impl

import android.content.res.Resources.NotFoundException
import com.adrian.commons.model.Response
import com.adrian.data.dao.AuthDao
import com.adrian.domain.mappers.toCredential
import com.adrian.domain.mappers.toDto
import com.adrian.domain.mappers.toUser
import com.adrian.domain.model.request.SignInRqDto
import com.adrian.domain.model.request.SignupUserRqDto
import com.adrian.domain.model.response.AuthUserRsDto
import com.adrian.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDao: AuthDao
) : AuthRepository {
    override fun authUser(credentials: SignInRqDto): Flow<Response<AuthUserRsDto>> = flow {
        emit(Response.Loading(true))

        val authUser = authDao.signIn(credentials.username, credentials.password)

        if (authUser?.id == null) {
            emit(Response.Failure(NotFoundException(), "Username or Password are incorrect"))
        } else {
            emit(Response.Success(authUser.toDto()))
        }
    }.flowOn(Dispatchers.IO)

    override fun registerUser(request: SignupUserRqDto): Flow<Response<AuthUserRsDto>> = flow  {
        emit(Response.Loading(true))

        val isUsernameAvailable = authDao.isUsernameTaken(request.username) == 0

        if (isUsernameAvailable) {
            val userId = authDao.insertUser(request.toUser())
            authDao.insertCredential(request.toCredential(userId))

            emit(Response.Success(AuthUserRsDto(
                id = userId,
                name = request.name,
                lastName = request.lastName
            )))
        } else {
            emit(Response.Failure(IllegalArgumentException(), "Username it's Not Available"))
        }
    }.flowOn(Dispatchers.IO)

}