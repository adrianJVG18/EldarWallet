package com.adrian.domain.errors

class EncryptionError(override val message: String = "Failed to encrypt data")
    : Exception(message) {
}