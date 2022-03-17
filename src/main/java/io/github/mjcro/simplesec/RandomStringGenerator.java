package io.github.mjcro.simplesec;

public interface RandomStringGenerator {
    /**
     * Generates random string of given size.
     *
     * @param size Required string size.
     * @return Generated string.
     */
    String generate(int size);
}
