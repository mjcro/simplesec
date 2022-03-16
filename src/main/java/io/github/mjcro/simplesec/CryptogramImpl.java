package io.github.mjcro.simplesec;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

/**
 * Basic cryptogram implementation.
 */
class CryptogramImpl implements Cryptogram {
    private final byte[] data;
    private final byte[] iv;

    CryptogramImpl(byte[] data, byte[] iv) {
        this.data = Objects.requireNonNull(data, "data");
        this.iv = Objects.requireNonNull(iv, "iv");
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public byte[] getIv() {
        return iv;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Cryptogram)) return false;
        Cryptogram that = (Cryptogram) o;
        return Arrays.equals(this.getData(), that.getData()) &&
                Arrays.equals(this.getIv(), that.getIv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(data), Arrays.hashCode(iv));
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "[Cryptogram IV:%d Data:%d]", iv.length, data.length);
    }
}
