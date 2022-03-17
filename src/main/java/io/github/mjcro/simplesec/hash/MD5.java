package io.github.mjcro.simplesec.hash;

/**
 * MD5 implementation.
 */
public class MD5 extends DigestHasher {
    /**
     * Hasher instance.
     */
    public static final MD5 INSTANCE = new MD5();

    public MD5() {
        super(16, "MD5");
    }
}
