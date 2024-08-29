package com.adrian.domain.encryption

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class AesEncryptionWithKeystore(private val keyProvider: KeyProvider) : CryptoGraph {

    override fun encrypt(plainData: String): String {
        val secretKey = keyProvider.getSecretKey()
        val iv = keyProvider.getIV()

        // Validar que la clave y el IV no sean nulos antes de usar en cipher.init()
        requireNotNull(secretKey) { "SecretKey is null." }
        requireNotNull(iv) { "IV is null." }

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)

        val encryptedBytes = cipher.doFinal(plainData.toByteArray(Charsets.UTF_8))
        val combined = cipher.iv + encryptedBytes
        return Base64.getEncoder().encodeToString(combined)
    }

    override fun decrypt(encryptedData: String): String {
        val combined = Base64.getDecoder().decode(encryptedData)
        val iv = combined.copyOfRange(0, 16)
        val encryptedBytes = combined.copyOfRange(16, combined.size)

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, keyProvider.getSecretKey(), IvParameterSpec(iv))

        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes, Charsets.UTF_8)
    }
}
