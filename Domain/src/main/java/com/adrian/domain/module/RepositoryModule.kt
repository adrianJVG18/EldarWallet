package com.adrian.domain.module

import com.adrian.data.dao.AuthDao
import com.adrian.data.dao.CardsDao
import com.adrian.domain.encryption.AesEncryption
import com.adrian.domain.encryption.CryptoGraph
import com.adrian.domain.repository.AuthRepository
import com.adrian.domain.repository.CardsRepository
import com.adrian.domain.repository.impl.AuthRepositoryImpl
import com.adrian.domain.repository.impl.CardsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun authService(
        authDao: AuthDao
    ): AuthRepository =
        AuthRepositoryImpl(authDao)

    @Singleton
    @Provides
    fun cryptoGraph(): CryptoGraph =
        AesEncryption()

    @Singleton
    @Provides
    fun cardsRepository(
         cryptoGraph: CryptoGraph,
         cardsDao: CardsDao
    ): CardsRepository =
        CardsRepositoryImpl(
            cryptoGraph =  cryptoGraph,
            cardsDao = cardsDao
        )


}