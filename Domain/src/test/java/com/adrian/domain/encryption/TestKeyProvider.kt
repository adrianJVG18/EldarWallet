package com.adrian.domain.encryption

import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class TestKeyProvider : KeyProvider {
    override fun getSecretKey(): SecretKey {
        // fixed keys
        val keyBytes = ByteArray(16) { 0x01 }
        return SecretKeySpec(keyBytes, "AES")
    }

    override fun getIV(): IvParameterSpec {
        // fixed IV
        val ivBytes = ByteArray(16) { 0x00 }
        return IvParameterSpec(ivBytes)
    }
}