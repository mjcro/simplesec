package io.github.mjcro.simplesec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public interface Cipher {
    /**
     * Encrypts provided binary data
     *
     * @param data Data to encrypt.
     * @return Encrypted cryptogram.
     */
    Cryptogram encrypt(byte[] data);

    /**
     * Decrypts provided binary cryptogram
     */
    byte[] decrypt(Cryptogram data);

    /**
     * Encrypts provided UTF-8 string.
     *
     * @param s Data to encrypt.
     * @return Encrypted cryptogram.
     */
    default Cryptogram encryptString(String s) {
        return encrypt(s.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encrypts provided binary data encoded in hexadecimal format.
     *
     * @param s Data to encrypt.
     * @return Encrypted cryptogram.
     */
    default Cryptogram encryptHex(String s) {
        return encrypt(HexUtil.decode(s));
    }

    /**
     * Encrypts provided binary data encoded in Base64 format.
     *
     * @param s Data to encrypt.
     * @return Encrypted cryptogram.
     */
    default Cryptogram encryptBase64(String s) {
        return encrypt(Base64.getDecoder().decode(s));
    }
}
