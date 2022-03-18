package io.github.mjcro.simplesec.crypt;

import io.github.mjcro.simplesec.Cryptogram;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class XORIVCipherTest {
    @Test
    public void testEncryptDecrypt() {
        XORIVCipher cipher = new XORIVCipher("This is secret");

        Cryptogram cryptogram = cipher.encryptPlainString("This is some data");
        Assert.assertEquals(cryptogram.getData().length, "This is some data".getBytes(StandardCharsets.UTF_8).length);
        Assert.assertEquals(cryptogram.getIv().length, "This is some data".getBytes(StandardCharsets.UTF_8).length);

        Assert.assertEquals(cipher.decryptToString(cryptogram), "This is some data");
    }
}