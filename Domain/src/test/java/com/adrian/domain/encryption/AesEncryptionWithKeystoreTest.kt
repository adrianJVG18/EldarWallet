package com.adrian.domain.encryption

import junit.framework.TestCase.assertEquals
import org.junit.Test

class AesEncryptionWithKeystoreTest {

    private val encryptor = AesEncryptionWithKeystore(TestKeyProvider())
    private val decryptor = AesEncryptionWithKeystore(TestKeyProvider())

    @Test
    fun `Given text when encrypted and decrypted should return original text`() {
        val originalText = "Hello Kotlin AES Encryption!"

        val result = encryptor.encrypt(originalText)

        assertEquals(originalText, decryptor.decrypt(result))
    }

}