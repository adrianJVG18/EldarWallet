package com.adrian.domain.encryption

import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
class AesEncryption : CryptoGraph {

    // TODO: secret passwords and key stores it's another subject to be defined.
    //  By the moment this is enough
    companion object {
        private const val SECRET_KEY = "tK5UT8lIi7GVUolBxya5XVsmeDCoUl6vHhdIEkjHh0UiiUOBihvSMB6sQ="
        private const val SALT = "QWlTlKHuygceIjn9876MkJTQWZ2bGhpV3U="
        private const val IV = "bVQzNFNhRkQIUYV896tvNJvi1Njc4UUFaWA=="
    }

    override fun encrypt(plainData: String): String {
        try {
            val ivParameterSpec = IvParameterSpec(Base64.decode(IV))
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            val spec = PBEKeySpec(SECRET_KEY.toCharArray(), Base64.decode(SALT), 10000, 256)
            val tmp = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)

            return cipher.doFinal(plainData.toByteArray()).toString()
        } catch (e: Exception) {
            println("Error while encrypting: $e")
        }
        return ""
    }

    override fun decrypt(encryptedData: String): String {
        try {
            val ivParameterSpec = IvParameterSpec(Base64.decode(IV))

            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            val spec =  PBEKeySpec(SECRET_KEY.toCharArray(), Base64.decode(SALT), 10000, 256)
            val tmp = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
            return  cipher.doFinal(encryptedData.toByteArray()).toString()
        } catch (e : Exception) {
            println("Error while decrypting: $e");
        }
        return ""
    }



}