package io.github.mjcro.simplesec.hashing;

/**
 * SHA256 implementation.
 */
public class SHA256 extends DigestHasher {
    /**
     * Hasher instance.
     */
    public static final SHA256 INSTANCE = new SHA256();

    public SHA256() {
        super(32, "SHA-256");
    }
}
