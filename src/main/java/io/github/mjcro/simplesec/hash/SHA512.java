package io.github.mjcro.simplesec.hash;

/**
 * SHA512 implementation.
 */
public class SHA512 extends DigestHasher {
    /**
     * Hasher instance.
     */
    public static final SHA512 INSTANCE = new SHA512();

    public SHA512() {
        super(64, "SHA-512");
    }
}
