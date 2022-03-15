package io.github.mjcro.simplesec.hashing;

import io.github.mjcro.simplesec.HexUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MD5Test {
    @Test
    public void testDefaults() {
        Assert.assertEquals(MD5.INSTANCE.size(), 16);
        Assert.assertEquals(MD5.INSTANCE.toString(), "MD5 hasher");
    }

    @Test
    public void testHashing() {
        Assert.assertEquals(MD5.INSTANCE.hashHex("Hello, world!"), "6cd3556deb0da54bca060b4c39479839");
        Assert.assertEquals(MD5.INSTANCE.hashHex("Hello,", " world!"), "6cd3556deb0da54bca060b4c39479839");
        Assert.assertEquals(MD5.INSTANCE.hashHex("Foo".getBytes(StandardCharsets.UTF_8)), "1356c67d7ad1638d816bfb822dd2c25d");

        Assert.assertEquals(HexUtil.encode(MD5.INSTANCE.hash()), "00000000000000000000000000000000");
    }

    @Test
    public void testFile() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/file.txt")) {
            Assert.assertEquals(MD5.INSTANCE.hashHex(is), "db89bb5ceab87f9c0fcc2ab36c189c2c");
        }
    }
}