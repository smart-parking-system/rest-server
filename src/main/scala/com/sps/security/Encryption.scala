package com.sps.security

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

class Encryption(key: String, iv: String) {
  def encrypt(data: String) = Encryption.encrypt(data, key, iv)
  def decrypt(data: String) = Encryption.decrypt(data, key, iv)
}

object Encryption {
  val CHARSET = "UTF-8"
  val ALGORITHM = "AES"
  val TRANSFORMATION = "AES/CBC/PKCS5PADDING"

  /**
   * Encrypts `data` using AES `key` & init vector `iv`
   * @param data - Data to encrypt
   * @param key - AES key
   * @param iv - Init Vector
   * @return String - Base64 encoded encrypted `data` string
   */
  def encrypt(data: String, key: String, iv: String): String = {
    val ivSpec = new IvParameterSpec(iv.getBytes(CHARSET))
    val keySpec = new SecretKeySpec(key.getBytes(CHARSET), ALGORITHM)

    val cipher = Cipher.getInstance(TRANSFORMATION)
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

    val encrypted = cipher.doFinal(data.getBytes())
    return Base64.getEncoder().encodeToString(encrypted)
  }

  /**
   * Denrypts `data` using AES `key` & init vector `iv`
   * @param data - Base64 encoded data to decrypt
   * @param key - AES key
   * @param iv - Init Vector
   * @return String - Decrypted `data` string
   */
  def decrypt(data: String, key: String, iv: String): String = {
    val ivSpec = new IvParameterSpec(iv.getBytes(CHARSET))
    val keySpec = new SecretKeySpec(key.getBytes(CHARSET), ALGORITHM)

    val cipher = Cipher.getInstance(TRANSFORMATION)
    cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
    val original = cipher.doFinal(Base64.getDecoder.decode(data))

    return new String(original)
  }

}
