package io.github.mjcro.simplesec.crypt;

import io.github.mjcro.simplesec.Cryptogram;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class XORIVCipher extends XORCipher {
    private final Random random;

    /**
     * Constructs new XOR cipher using given secret
     * represented as bytes.
     *
     * @param random Random generator.
     * @param secret Secret.
     */
    public XORIVCipher(Random random, byte[] secret) {
        super(secret);
        this.random = random == null ? new SecureRandom() : random;
    }

    /**
     * Constructs new XOR cipher using given secret
     * represented as bytes.
     *
     * @param secret Secret.
     */
    public XORIVCipher(byte[] secret) {
        this(null, secret);
    }

    /**
     * Constructs new XOR cipher using given secret
     * represented as UTF-8 string.
     *
     * @param secret String.
     */
    public XORIVCipher(String secret) {
        this(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Cryptogram encrypt(byte[] data) {
        // Generating random IV with same length as data
        byte[] iv = new byte[data.length];
        random.nextBytes(iv);
        // "Encrypting"
        return Cryptogram.create(
                XOR(data, iv),  // Data is encrypted using random IV
                XOR(iv, secret) // IV is encrypted using secret
        );
    }

    @Override
    public byte[] decrypt(Cryptogram data) {
        // Decrypting iv
        byte[] iv = XOR(data.getIv(), secret);

        // Decrypting data
        return XOR(data.getData(), iv);
    }
}
