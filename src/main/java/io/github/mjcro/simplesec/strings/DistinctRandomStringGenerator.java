package io.github.mjcro.simplesec.strings;

import java.util.Random;

public class DistinctRandomStringGenerator extends AbstractDictionaryRandomStringGenerator {
    private static final char[] dictionary = new char[]{
            '3',
            '4',
            '6',
            '7',
            '8',
            '9',
            'q',
            'w',
            'e',
            'r',
            't',
            'y',
            'u',
            'p',
            'a',
            'd',
            'f',
            'g',
            'h',
            'j',
            'k',
            'z',
            'x',
            'c',
            'v',
            'b',
            'n',
            'm',
    };

    public DistinctRandomStringGenerator() {
        this(null);
    }

    DistinctRandomStringGenerator(Random random) {
        super(random);
    }

    @Override
    public char[] getDictionary() {
        return dictionary;
    }
}
