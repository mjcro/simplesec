package io.github.mjcro.simplesec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public interface Cipher {
    /**
     * Encrypts provided binary data.
     *
     * @param data Data to encrypt.
     * @return Encrypted cryptogram.
     */
    Cryptogram encrypt(byte[] data);

    /**
     * Decrypts provided binary cryptogram.
     *
     * @param data Cryptogram to decrypt.
     * @return Decrypted data.
     */
    byte[] decrypt(Cryptogram data);

    /**
     * Encrypts provided UTF-8 string.
     *
     * @param s Data to encrypt.
     * @return Encrypted cryptogram.
     */
    default Cryptogram encryptPlainString(String s) {
        return encrypt(s.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encrypts provided binary data encoded in hexadecimal format.
     *
     * @param s Data to encrypt.
     * @return Encrypted cryptogram.
     */
    default Cryptogram encryptHexString(String s) {
        return encrypt(HexUtil.decode(s));
    }

    /**
     * Encrypts provided binary data encoded in Base64 format.
     *
     * @param s Data to encrypt.
     * @return Encrypted cryptogram.
     */
    default Cryptogram encryptBase64String(String s) {
        return encrypt(Base64.getDecoder().decode(s));
    }

    /**
     * Decrypts provided binary cryptogram.
     *
     * @param data Cryptogram to decrypt.
     * @return Decrypted data.
     */
    default String decryptToString(Cryptogram data) {
        return new String(decrypt(data), StandardCharsets.UTF_8);
    }
}
