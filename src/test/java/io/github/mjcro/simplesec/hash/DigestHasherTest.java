package io.github.mjcro.simplesec.hash;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

    @DataProvider
    public Object[][] standardDigestHasherDataProvider() {
        return new Object[][]{
                {MD5.INSTANCE, 16, "MD5 hasher", "6cd3556deb0da54bca060b4c39479839"},
                {SHA1.INSTANCE, 20, "SHA1 hasher", "943a702d06f34599aee1f8da8ef9f7296031d699"},
                {SHA256.INSTANCE, 32, "SHA-256 hasher", "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3"},
                {SHA384.INSTANCE, 48, "SHA-384 hasher", "55bc556b0d2fe0fce582ba5fe07baafff035653638c7ac0d5494c2a64c0bea1cc57331c7c12a45cdbca7f4c34a089eeb"},
                {SHA512.INSTANCE, 64, "SHA-512 hasher", "c1527cd893c124773d811911970c8fe6e857d6df5dc9226bd8a160614c0cd963a4ddea2b94bb7d36021ef9d865d5cea294a82dd49a0bb269f51f6e7a57f79421"},
        };
    }

    @Test(dataProvider = "standardDigestHasherDataProvider")
    public void testStandardDigestHashers(
            DigestHasher hasher,
            int size,
            String name,
            String expected
    ) {
        Assert.assertEquals(hasher.size(), size);
        Assert.assertEquals(hasher.toString(), name);
        Assert.assertEquals(hasher.hashHex("Hello, world!"), expected);
        Assert.assertEquals(hasher.hash("foo").length, hasher.size());
    }
}