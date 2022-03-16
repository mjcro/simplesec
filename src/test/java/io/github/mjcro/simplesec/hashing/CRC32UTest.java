package io.github.mjcro.simplesec.hashing;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CRC32UTest {
    @Test
    public void testHashing() {
        Assert.assertEquals(CRC32U.INSTANCE.hashHex("Hello, world!"), "ebe6c6e6");
        Assert.assertEquals(CRC32U.INSTANCE.checksum("Hello, world!"), 3957769958L);

        Assert.assertEquals(CRC32U.INSTANCE.checksum((byte[]) null), 0L);
        Assert.assertEquals(CRC32U.INSTANCE.checksum(new byte[0]), 0L);
    }
}