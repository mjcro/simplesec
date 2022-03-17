package io.github.mjcro.simplesec.strings;

import io.github.mjcro.simplesec.RandomStringGenerator;

import java.security.SecureRandom;
import java.util.Random;

abstract class AbstractRandomStringGenerator implements RandomStringGenerator {
    private final Random random;

    protected AbstractRandomStringGenerator(Random random) {
        this.random = random == null ? new SecureRandom() : random;
    }

    @Override
    public String generate(int size) {
        if (size < 1 || size > 1024) {
            throw new IllegalArgumentException("Illegal string size " + size);
        }
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return convert(bytes);
    }

    abstract String convert(byte[] bytes);
}
