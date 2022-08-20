package com.examples.core.utils

import java.security.Key
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

object RSAEncryptionUtils {

    private const val TRANSFORMATION_METHOD = "RSA/ECB/PKCS1Padding"
    private const val ENCRYPTION_ALGORITHM = "RSA"
    private const val KEY = ""

    fun encryptString(encryptionText: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION_METHOD)
        cipher.init(Cipher.ENCRYPT_MODE, loadPublicKey())
        return encode(cipher.doFinal(encryptionText.toByteArray()))
    }

    private fun loadPublicKey(): Key {
        val data: ByteArray = android.util.Base64.decode(
            KEY.toByteArray(),
            android.util.Base64.DEFAULT
        )
        val spec = X509EncodedKeySpec(data)
        val fact = KeyFactory.getInstance(ENCRYPTION_ALGORITHM)
        return fact.generatePublic(spec)
    }

    private fun encode(data: ByteArray): String {
        return android.util.Base64.encodeToString(data, android.util.Base64.DEFAULT)
    }
}