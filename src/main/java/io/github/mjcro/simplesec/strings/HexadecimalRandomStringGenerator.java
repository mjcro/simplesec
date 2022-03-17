package io.github.mjcro.simplesec.strings;

import io.github.mjcro.simplesec.HexUtil;

import java.util.Random;

public class HexadecimalRandomStringGenerator extends AbstractRandomStringGenerator {
    public HexadecimalRandomStringGenerator() {
        this(null);
    }

    HexadecimalRandomStringGenerator(Random random) {
        super(random);
    }

    @Override
    String convert(byte[] bytes) {
        return HexUtil.encode(bytes).substring(0, bytes.length);
    }
}
