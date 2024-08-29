package com.adrian.domain.module

import com.adrian.domain.encryption.AesEncryption
import com.adrian.domain.encryption.CryptoGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EncryptionModule {

    /*
    @Singleton
    @Provides
    fun keyProvider(): KeyProvider =
        KeystoreKeyProvider()

    @Singleton
    @Provides
    fun cryptoGraph(keyProvider: KeyProvider): CryptoGraph =
        AesEncryptionWithKeystore(keyProvider)

     */

    @Singleton
    @Provides
    fun cryptoGraph(): CryptoGraph =
        AesEncryption()

}