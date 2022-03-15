package io.github.mjcro.simplesec;

public class HexUtil {
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    /**
     * Encodes given byte array into hexadecimal string.
     *
     * @param bytes Source byte array.
     * @return Hexadecimal representation.
     */
    public static String encode(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }

        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Private constructor for utility class.
     */
    private HexUtil() {
    }
}
