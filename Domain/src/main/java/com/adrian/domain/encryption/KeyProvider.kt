package com.adrian.domain.encryption

import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

interface KeyProvider {
    fun getSecretKey(): SecretKey
    fun getIV(): IvParameterSpec
}