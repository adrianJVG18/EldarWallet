package com.adrian.domain.repository.impl

import android.content.SharedPreferences
import android.content.res.Resources.NotFoundException
import com.adrian.commons.model.Response
import com.adrian.data.dao.AuthDao
import com.adrian.domain.mappers.toCredential
import com.adrian.domain.mappers.toDto
import com.adrian.domain.mappers.toUser
import com.adrian.domain.model.request.SignInRqDto
import com.adrian.domain.model.request.SignupUserRqDto
import com.adrian.domain.model.response.AuthUserRsDto
import com.adrian.domain.model.response.UserCredentialsRsDto
import com.adrian.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
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


    override fun getAuthenticatedUser(): Flow<Response<UserCredentialsRsDto>> = flow  {
        emit(Response.Loading(true))

        val username = sharedPreferences.getString(USERNAME_KEY, "") ?: ""
        val password = sharedPreferences.getString(PASSWORD_KEY, "") ?: ""

        if (username.isNotBlank() && password.isNotBlank()) {
            emit(Response.Success(UserCredentialsRsDto(username, password)))
        } else {
            emit(Response.Failure(NotFoundException(), "There is no previously logged in User"))
        }
    }.flowOn(Dispatchers.IO)

    override fun persisAuthenticatedUser(username: String, password: String): Flow<Response<Boolean>> = flow {
        emit(Response.Loading(true))

        sharedPreferences.edit()
            .putString(USERNAME_KEY, username)
            .putString(PASSWORD_KEY, password)
            .apply()

        emit(Response.Success(true))
    }.flowOn(Dispatchers.IO)


    companion object {
        private const val USERNAME_KEY = "USERNAME_KEY"
        private const val PASSWORD_KEY = "PASSWORD_KEY"
    }
}