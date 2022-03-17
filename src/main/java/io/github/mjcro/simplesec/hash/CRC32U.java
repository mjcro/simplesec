package io.github.mjcro.simplesec.hash;

import io.github.mjcro.simplesec.HasherUint;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * CRC32 implementation, compatible with PHP (crc32b) and MySQL.
 * Produces unsigned integer values
 */
public final class CRC32U implements HasherUint {
    /**
     * Hasher instance.
     */
    public static final CRC32U INSTANCE = new CRC32U();

    @Override
    public long checksum(byte[] source) {
        if (source == null || source.length == 0) {
            return 0;
        }

        Checksum checksum = new CRC32();
        checksum.update(source, 0, source.length);
        return checksum.getValue();
    }
}