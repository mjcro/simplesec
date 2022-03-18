package io.github.mjcro.simplesec.crypt;

import io.github.mjcro.simplesec.Cipher;
import io.github.mjcro.simplesec.Cryptogram;

import java.nio.charset.StandardCharsets;

/**
 * Simple naïve XOR-based cipher.
 */
public class XORCipher implements Cipher {
    protected final byte[] secret;

    /**
     * Performs XOR operation over given data using secret.
     * If data length greater that secret length, secret
     * will be re-read from beginning.
     *
     * @param data   Data to encrypt.
     * @param secret Secret key.
     * @return Encrypted data.
     */
    public static byte[] XOR(byte[] data, byte[] secret) {
        if (secret == null || secret.length == 0) {
            throw new IllegalArgumentException("Empty secret");
        }
        if (data == null || data.length == 0) {
            return new byte[0];
        }

        int len = data.length;
        int slen = secret.length;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = (byte) (data[i] ^ secret[i % slen]);
        }
        return result;
    }

    /**
     * Constructs new XOR cipher using given secret
     * represented as bytes.
     *
     * @param secret Secret.
     */
    public XORCipher(byte[] secret) {
        if (secret == null || secret.length == 0) {
            throw new IllegalArgumentException("Empty secret for XOR cipher");
        }
        this.secret = secret;
    }

    /**
     * Constructs new XOR cipher using given secret
     * represented as UTF-8 string.
     *
     * @param secret String.
     */
    public XORCipher(String secret) {
        this(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Cryptogram encrypt(byte[] data) {
        return Cryptogram.create(xorSecret(data), new byte[0]);
    }

    @Override
    public byte[] decrypt(Cryptogram data) {
        return xorSecret(data.getData());
    }

    /**
     * Performs XOR operation over given bytes and internal secret.
     *
     * @param bytes Given bytes.
     * @return Resulting bytes after XOR.
     */
    public byte[] xorSecret(byte[] bytes) {
        return XOR(bytes, this.secret);
    }
}
