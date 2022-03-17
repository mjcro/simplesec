package io.github.mjcro.simplesec.crypt;

import io.github.mjcro.simplesec.Cryptogram;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Abstract Cipher.
 */
public abstract class AbstractCipher implements io.github.mjcro.simplesec.Cipher {
    private final Random random;
    private final byte[] key;

    /**
     * Protected constructor.
     *
     * @param random Random to use. Not {@link java.security.SecureRandom} should be used only for tests.
     * @param key    Secret key.
     */
    protected AbstractCipher(Random random, byte[] key) {
        this.random = Objects.requireNonNull(random, "random");
        this.key = Objects.requireNonNull(key, "key");
    }

    @Override
    public Cryptogram encrypt(byte[] data) {
        Objects.requireNonNull(data, "data");
        try {
            Cipher c = Cipher.getInstance(getCipherName());
            IvParameterSpec iv = generateIv(c.getBlockSize());
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, getAlgorithmName()), iv);
            return Cryptogram.create(c.doFinal(data), c.getIV());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] decrypt(Cryptogram data) {
        Objects.requireNonNull(data, "data");
        try {
            Cipher cipher = Cipher.getInstance(getCipherName());
            cipher.init(
                    Cipher.DECRYPT_MODE,
                    new SecretKeySpec(key, getAlgorithmName()),
                    new IvParameterSpec(data.getIv())
            );
            return cipher.doFinal(data.getData());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates Initialization Vector (IV) from random bytes
     *
     * @param size Key size (in bytes)
     * @return Generated IV
     */
    protected IvParameterSpec generateIv(final int size) {
        byte[] ivBytes = new byte[size];
        random.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }

    /**
     * @return Cipher name.
     */
    abstract String getCipherName();

    /**
     * @return Cipher algorithm name.
     */
    abstract String getAlgorithmName();

    /**
     * Checking basic encryption/decryption to validate that java cryptography settings are correct.
     * This method is expected to be run just once during application startup.
     */
    protected void checkCipherSettings() {
        byte[] original = new byte[1000];
        new Random().nextBytes(original);
        // Encrypting, decrypting and checking equality
        byte[] decrypted;
        try {
            Cryptogram cryptogram = encrypt(original);
            decrypted = decrypt(cryptogram);
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred during encryption/decryption attempt."
                    + " Please check java cryptography settings. Is JCE Unlimited Strength activated?", e);
        }

        if (!Arrays.equals(original, decrypted)) {
            throw new IllegalStateException(
                    "Decrypted payload does not match original. Check java cryptography settings."
            );
        }
    }
}
