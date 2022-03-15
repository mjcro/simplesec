package io.github.mjcro.simplesec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

/**
 * Describes algorithms, that are able to produce hash value.
 */
public interface Hasher {
    int STREAM_BUFFER_LENGTH = 256;

    /**
     * Matches two given hashes and returns true if they
     * are equal.
     * Case-insensitive, nulls will not produce NPE but false.
     *
     * @param a First hash.
     * @param b Second hash.
     * @return True if two hashes seems to be equal.
     */
    static boolean hashEquals(String a, String b) {
        return a != null && b != null && a.trim().equalsIgnoreCase(b.trim());
    }

    /**
     * @return Byte size length of produced hash.
     */
    int size();

    /**
     * Calculates hash over provided data.
     *
     * @param data Data to hash
     * @return Hash
     */
    byte[] hash(byte[] data);



    /* ------------- Helpers returning byte arrays ------------- */


    /**
     * @param data Set of  bytes arrays
     * @return Hash of concatenation bytes
     */
    default byte[] hash(byte[]... data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Empty data to hash");
        }
        if (data.length == 1) {
            return this.hash(data[0]);
        }

        // Concatenating all arrays
        int len = 0;
        for (byte[] d : data) {
            len += d.length;
        }

        int cursor = 0;
        byte[] result = new byte[len];
        for (byte[] d : data) {
            System.arraycopy(d, 0, result, cursor, d.length);
            cursor += d.length;
        }

        return this.hash(result);
    }

    /**
     * @return Empty hash
     */
    default byte[] hash() {
        byte[] hash = new byte[size()];
        Arrays.fill(hash, (byte) 0);
        return hash;
    }

    /**
     * Calculates provided string hash.
     *
     * @param data Source data
     * @return Hash
     */
    default byte[] hash(String data) {
        if (data == null) {
            return hash();
        }

        return hash(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Calculates hash of provided objects - they will be concatenated.
     *
     * @param data Data to hash
     * @return Hash
     */
    default byte[] hash(Object... data) {
        if (data == null || data.length == 0) {
            return hash();
        }

        StringBuilder sb = new StringBuilder();
        for (Object o : data) {
            if (o != null) {
                sb.append(o.toString());
            }
        }

        return hash(sb.toString());
    }

    /**
     * Performs String.format over given values and then applies hash.
     *
     * @param pattern String.format pattern.
     * @param args    String.format arguments.
     * @return Hash
     */
    default byte[] hashFormat(String pattern, Object... args) {
        return hash(String.format(Locale.ROOT, pattern, args));
    }

    /**
     * Calculates hash of {@link InputStream} data.
     *
     * @param is Source input stream.
     * @return Hash data.
     */
    default byte[] hash(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[STREAM_BUFFER_LENGTH];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }



    /* ------------- Helpers returning hexadecimal ------------- */


    /**
     * Calculates hash over provided data.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, lowercase.
     */
    default String hashHex(byte[] data) {
        return HexUtil.encode(hash(data));
    }

    /**
     * Calculates hash over provided data.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, uppercase.
     */
    default String hashHexUpper(byte[] data) {
        return hashHex(data).toUpperCase(Locale.ROOT);
    }

    /**
     * Calculates provided string hash.
     *
     * @param data Source data
     * @return Hash in hexadecimal representation, lowercase.
     */
    default String hashHex(String data) {
        return HexUtil.encode(hash(data));
    }

    /**
     * Calculates provided string hash.
     *
     * @param data Source data
     * @return Hash in hexadecimal representation, uppercase.
     */
    default String hashHexUpper(String data) {
        return hashHex(data).toUpperCase(Locale.ROOT);
    }

    /**
     * Calculates hash of provided objects - they will be concatenated.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, lowercase.
     */
    default String hashHex(byte[]... data) {
        return HexUtil.encode(hash(data));
    }

    /**
     * Calculates hash of provided objects - they will be concatenated.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, uppercase.
     */
    default String hashHexUpper(byte[]... data) {
        return hashHex(data).toUpperCase(Locale.ROOT);
    }

    /**
     * Calculates hash of provided objects - they will be concatenated.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, lowercase.
     */
    default String hashHex(Object... data) {
        return HexUtil.encode(hash(data));
    }

    /**
     * Calculates hash of provided objects - they will be concatenated.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, uppercase.
     */
    default String hashHexUpper(Object... data) {
        return hashHex(data).toUpperCase(Locale.ROOT);
    }

    /**
     * Performs String.format over given values and then applies hash.
     *
     * @param pattern String.format pattern.
     * @param args    String.format arguments.
     * @return Hash in hexadecimal representation, lowercase.
     */
    default String hashFormatHex(String pattern, Object... args) {
        return HexUtil.encode(hashFormat(pattern, args));
    }

    /**
     * Performs String.format over given values and then applies hash.
     *
     * @param pattern String.format pattern.
     * @param args    String.format arguments.
     * @return Hash in hexadecimal representation, uppercase.
     */
    default String hashFormatHexUpper(String pattern, Object... args) {
        return hashFormatHex(pattern, args).toUpperCase(Locale.ROOT);
    }

    /**
     * Calculates hash over provided data.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, lowercase.
     */
    default String hashHex(InputStream data) throws IOException {
        return HexUtil.encode(hash(data));
    }

    /**
     * Calculates hash over provided data.
     *
     * @param data Data to hash
     * @return Hash in hexadecimal representation, uppercase.
     */
    default String hashHexUpper(InputStream data) throws IOException {
        return hashHex(data).toUpperCase(Locale.ROOT);
    }

    /**
     * Calculates hash over provided file
     *
     * @param file File to hash
     * @return Hash in hexadecimal representation, lowercase.
     */
    default String hashHex(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return HexUtil.encode(hash(fis));
        }
    }

    /**
     * Calculates hash over provided file
     *
     * @param file File to hash
     * @return Hash in hexadecimal representation, uppercase.
     */
    default String hashHexUpper(File file) throws IOException {
        return hashHex(file).toUpperCase(Locale.ROOT);
    }


    /* ------------- Verification helpers ------------- */


    /**
     * Creates hash for given data and then verifies that
     * if matches given hash.
     *
     * @param hash Hash candidate.
     * @param data Data to hash.
     * @return True if hashes matches.
     */
    default boolean verify(String hash, byte[] data) {
        return hashEquals(hash, hashHex(data));
    }

    /**
     * Creates hash for given data and then verifies that
     * if matches given hash.
     *
     * @param hash Hash candidate.
     * @param data Data to hash.
     * @return True if hashes matches.
     */
    default boolean verify(String hash, String data) {
        return hashEquals(hash, hashHex(data));
    }

    /**
     * Creates hash for given data and then verifies that
     * if matches given hash.
     *
     * @param hash Hash candidate.
     * @param data Data to hash.
     * @return True if hashes matches.
     */
    default boolean verify(String hash, Object... data) {
        return hashEquals(hash, hashHex(data));
    }

    /**
     * Performs String.format over given values and then applies hash.
     *
     * @param hash    Hash candidate.
     * @param pattern String.format pattern.
     * @param args    String.format arguments.
     * @return True if hashes matches.
     */
    default boolean verifyFormatHex(String hash, String pattern, Object... args) {
        return hashEquals(hash, HexUtil.encode(hashFormat(pattern, args)));
    }
}