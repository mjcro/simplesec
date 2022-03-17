package io.github.mjcro.simplesec.strings;

import java.math.BigInteger;
import java.util.Random;

public abstract class AbstractDictionaryRandomStringGenerator extends AbstractRandomStringGenerator {
    protected AbstractDictionaryRandomStringGenerator(Random random) {
        super(random);
    }

    @Override
    String convert(byte[] bytes) {
        char[] dictionary = getDictionary();
        BigInteger modulus = BigInteger.valueOf(dictionary.length);
        BigInteger bi = new BigInteger(bytes);
        char[] response = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            BigInteger idx = bi.mod(modulus);
            response[i] = dictionary[idx.intValue()];
            bi = bi.divide(modulus);
        }
        return new String(response);
    }

    public int entropy(int size) {
        return (int) (Math.log(getDictionary().length) / Math.log(2) * size);
    }

    public abstract char[] getDictionary();
}
