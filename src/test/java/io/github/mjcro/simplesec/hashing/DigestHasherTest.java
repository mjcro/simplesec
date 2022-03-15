package io.github.mjcro.simplesec.hashing;

import org.testng.annotations.Test;

public class DigestHasherTest {
    @Test(expectedExceptions = RuntimeException.class)
    public void testInvalidDigest() {
        new InvalidDigest();
    }

    private static class InvalidDigest extends DigestHasher {
        InvalidDigest() {
            super(16, "MD007");
        }
    }
}