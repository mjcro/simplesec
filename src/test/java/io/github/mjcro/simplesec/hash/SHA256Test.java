package io.github.mjcro.simplesec.hash;

import io.github.mjcro.simplesec.HexUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class SHA256Test {
    @Test
    public void testDefaults() {
        Assert.assertEquals(SHA256.INSTANCE.size(), 32);
        Assert.assertEquals(SHA256.INSTANCE.toString(), "SHA-256 hasher");
    }

    @Test
    public void testHashing() {
        Assert.assertEquals(SHA256.INSTANCE.hashHex("Hello, world!"), "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3");
        Assert.assertEquals(SHA256.INSTANCE.hashHex("Hello,", " world!"), "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3");
        Assert.assertEquals(SHA256.INSTANCE.hashHex("Hello,".getBytes(StandardCharsets.UTF_8), " world!".getBytes(StandardCharsets.UTF_8)), "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3");
        Assert.assertEquals(SHA256.INSTANCE.hashHex("Foo".getBytes(StandardCharsets.UTF_8)), "1cbec737f863e4922cee63cc2ebbfaafcd1cff8b790d8cfd2e6a5d550b648afa");
        Assert.assertEquals(SHA256.INSTANCE.hashFormatHex("%s, %s!", "Hello", "world"), "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3");

        Assert.assertEquals(HexUtil.encode(SHA256.INSTANCE.hash()), "0000000000000000000000000000000000000000000000000000000000000000");
    }
}