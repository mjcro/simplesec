package io.github.mjcro.simplesec.crypt;

import io.github.mjcro.simplesec.Cipher;
import io.github.mjcro.simplesec.Cryptogram;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class AesCbcPkcs5CipherTest {
    @Test
    public void testAes256() {
        Cipher cipher = AesCbcPkcs5Cipher.AES256PlainSHA("Some password");
        Cryptogram cryptogram = cipher.encryptPlainString("Hello, world");
        byte[] decrypted = cipher.decrypt(cryptogram);

        Assert.assertEquals(new String(decrypted, StandardCharsets.UTF_8), "Hello, world");
        Assert.assertEquals(cryptogram.getData().length, 16);
        Assert.assertEquals(cryptogram.getIv().length, 16);
    }
}