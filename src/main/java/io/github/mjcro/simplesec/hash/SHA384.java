package io.github.mjcro.simplesec.hash;

/**
 * SHA256 implementation.
 */
public class SHA384 extends DigestHasher {
    /**
     * Hasher instance.
     */
    public static final SHA384 INSTANCE = new SHA384();

    public SHA384() {
        super(48, "SHA-384");
    }
}
