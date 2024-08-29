package com.adrian.domain.encryption

import junit.framework.TestCase.assertEquals
import org.junit.Test

class AesEncryptionTest {

    private val encryptor = AesEncryption()
    private val decryptor = AesEncryption()

    @Test
    fun `Given text when encrypted and decrypted should return original text`() {
        val originalText = "Hello Kotlin AES Encryption!"

        val result = encryptor.encrypt(originalText)

        assertEquals(originalText, encryptor.decrypt(result))
    }

    @Test
    fun `Given text when encrypted and decrypted by different instance should return original text`() {
        val originalText = "Hello Kotlin AES Encryption!"

        val result = encryptor.encrypt(originalText)

        assertEquals(originalText, decryptor.decrypt(result))
    }

}