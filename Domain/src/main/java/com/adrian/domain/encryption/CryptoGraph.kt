package com.adrian.domain.encryption

interface CryptoGraph {
    fun encrypt(plainData: String): String
    fun decrypt(encryptedData: String): String
}