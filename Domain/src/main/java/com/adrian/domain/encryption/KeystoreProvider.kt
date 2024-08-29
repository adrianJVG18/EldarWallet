package com.adrian.domain.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class KeystoreKeyProvider : KeyProvider {

    private val keyAlias = "MyEncryptionKeyAlias"
    private val keystore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }

    override fun getSecretKey(): SecretKey {
        val key = if (keystore.containsAlias(keyAlias)) {
            keystore.getKey(keyAlias, null) as? SecretKey
        } else {
            createKey()
        }
        requireNotNull(key) { "Failed to get SecretKey from Keystore. Key is null." }
        return key
    }

    override fun getIV(): IvParameterSpec {
        val iv = ByteArray(16)
        java.security.SecureRandom().nextBytes(iv)
        return IvParameterSpec(iv)
    }

    private fun createKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            keyAlias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setKeySize(256)
            .build()
        keyGen.init(keyGenParameterSpec)
        return keyGen.generateKey()
    }
}
