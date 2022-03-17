package io.github.mjcro.simplesec.crypt;

import io.github.mjcro.simplesec.HexUtil;
import io.github.mjcro.simplesec.hash.SHA256;
import io.github.mjcro.simplesec.hash.SHA512;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Cipher implementation, that uses only Cipher Block Chaining (CBC).
 */
public final class AesCbcPkcs5Cipher extends AbstractCipher {
    /**
     * Constructs new AES 256 CBC cipher with PKCS5 padding.
     *
     * @param key Secret key, must be 32 bytes long.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES256(byte[] key) {
        if (key == null || key.length != 32) {
            throw new IllegalArgumentException("AES256 requires exactly 256 bits (32 bytes) key");
        }

        return new AesCbcPkcs5Cipher(key);
    }

    /**
     * Constructs new AES 256 CBC cipher with PKCS5 padding.
     *
     * @param hexkey Secret key in hexadecimal format, must be 64 characters long.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES256Hex(String hexkey) {
        return AES256(HexUtil.decode(hexkey));
    }

    /**
     * Constructs new AES 256 CBC cipher with PKCS5 padding.
     * Given key will be processed using SHA function.
     *
     * @param key Secret key with any length.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES256SHA(byte[] key) {
        return AES256(SHA256.INSTANCE.hash(key));
    }

    /**
     * Constructs new AES 256 CBC cipher with PKCS5 padding.
     * Given key will be processed using SHA function.
     *
     * @param key Secret key with any length.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES256PlainSHA(String key) {
        return AES256(SHA256.INSTANCE.hash(key));
    }

    /**
     * Constructs new AES 512 CBC cipher with PKCS5 padding.
     *
     * @param key Secret key, must be 64 bytes long.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES512(byte[] key) {
        if (key == null || key.length != 64) {
            throw new IllegalArgumentException("AES512 requires exactly 512 bits (64 bytes) key");
        }

        return new AesCbcPkcs5Cipher(key);
    }

    /**
     * Constructs new AES 512 CBC cipher with PKCS5 padding.
     *
     * @param hexkey Secret key in hexadecimal format, must be 128 characters long.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES512Hex(String hexkey) {
        return AES512(HexUtil.decode(hexkey));
    }

    /**
     * Constructs new AES 512 CBC cipher with PKCS5 padding.
     * Given key will be processed using SHA function.
     *
     * @param key Secret key with any length.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES512SHA(byte[] key) {
        return AES512(SHA512.INSTANCE.hash(key));
    }

    /**
     * Constructs new AES 512 CBC cipher with PKCS5 padding.
     * Given key will be processed using SHA function.
     *
     * @param key Secret key with any length.
     * @return Cipher.
     */
    public static AesCbcPkcs5Cipher AES512PlainSHA(String key) {
        return AES512(SHA512.INSTANCE.hash(key));
    }

    /**
     * Private constructor.
     *
     * @param random Random to use. Should be always {@link java.security.SecureRandom}.
     * @param key    Secret key.
     */
    AesCbcPkcs5Cipher(Random random, byte[] key) {
        super(random, key);

        // Checking that java cryptography settings are correct
        checkCipherSettings();
    }

    /**
     * Private constructor that uses only {@link java.security.SecureRandom}.
     *
     * @param key Secret key.
     */
    AesCbcPkcs5Cipher(byte[] key) {
        this(new SecureRandom(), key);
    }

    @Override
    String getCipherName() {
        return "AES/CBC/PKCS5Padding";
    }

    @Override
    String getAlgorithmName() {
        return "AES";
    }
}
