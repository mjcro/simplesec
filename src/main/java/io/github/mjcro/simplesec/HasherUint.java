package io.github.mjcro.simplesec;

/**
 * Describes hash algorithms, that produces unsigned integer values.
 * Due Java restrictions, return value will be long, but it will be packed into byte[4] array
 */
public interface HasherUint extends Hasher {
    /**
     * Calculates provided byte array hash.
     *
     * @param data Byte array
     * @return Hash
     */
    long checksum(byte[] data);

    /**
     * Calculates provided string hash.
     *
     * @param data String source data
     * @return Hash
     */
    default long checksum(final String data) {
        if (data == null) {
            return 0L;
        }

        return checksum(data.getBytes());
    }

    /**
     * Calculates hash of provided objects - they will be concatenated.
     *
     * @param data Data to hash
     * @return Hash
     */
    default long checksum(final Object... data) {
        if (data == null || data.length == 0) {
            return 0L;
        }

        StringBuilder sb = new StringBuilder();
        for (Object o : data) {
            if (o != null) {
                sb.append(o.toString());
            }
        }

        return checksum(sb.toString());
    }

    @Override
    default byte[] hash(final byte[] data) {
        long value = checksum(data);
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    @Override
    default int size() {
        return 4;
    }
}