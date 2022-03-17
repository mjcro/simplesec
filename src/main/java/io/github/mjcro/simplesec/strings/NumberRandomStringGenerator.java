package io.github.mjcro.simplesec.strings;

import java.util.Random;

public class NumberRandomStringGenerator extends AbstractDictionaryRandomStringGenerator {
    private static final char[] dictionary = new char[]{
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9'
    };

    public NumberRandomStringGenerator() {
        this(null);
    }

    NumberRandomStringGenerator(Random random) {
        super(random);
    }

    @Override
    public char[] getDictionary() {
        return dictionary;
    }
}
