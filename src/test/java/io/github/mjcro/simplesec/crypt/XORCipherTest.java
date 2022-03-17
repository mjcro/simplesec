package io.github.mjcro.simplesec.crypt;

import io.github.mjcro.simplesec.Cipher;
import io.github.mjcro.simplesec.Cryptogram;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class XORCipherTest {
    @Test
    public void testXor() {
        XORCipher xor = new XORCipher("SeCReT");
        byte[] origin = "Foo".getBytes(StandardCharsets.UTF_8);
        byte[] xor1 = xor.xor(origin);
        byte[] xor2 = xor.xor(xor1);

        Assert.assertEquals(xor2, origin);
        Assert.assertNotEquals(xor1, xor2);
    }

    @Test(dependsOnMethods = "testXor")
    public void testEncrypt() {
        Cipher xor = new XORCipher("Some secret");
        Cryptogram data = xor.encryptString("Some data");

        Assert.assertEquals(data.getIv().length, 0);
        Assert.assertEquals(data.getDataHex(), "000000000017041713");
    }

    @Test(dependsOnMethods = "testEncrypt")
    public void testDecrypt() {
        Cipher xor = new XORCipher("Some secret");
        byte[] decrypted = xor.decrypt(Cryptogram.fromHex("000000000017041713", ""));
        Assert.assertEquals(new String(decrypted, StandardCharsets.UTF_8), "Some data");
    }
}