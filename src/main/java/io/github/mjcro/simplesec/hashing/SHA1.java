package io.github.mjcro.simplesec.hashing;

/**
 * SHA1 implementation.
 */
public class SHA1 extends DigestHasher {
    /**
     * Hasher instance.
     */
    public static final SHA1 INSTANCE = new SHA1();

    public SHA1() {
        super(20, "SHA1");
    }
}