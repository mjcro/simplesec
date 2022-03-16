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
     * Decodes given string in hexadecimal format into bytes.
     *
     * @param s Hexadecimal string.
     * @return Byte array.
     */
    public static byte[] decode(String s) {
        if (s == null || s.length() == 0) {
            return new byte[0];
        }
        if (s.length() % 2 != 0) {
            throw new IllegalArgumentException("Incorrect hex string " + s);
        }
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Private constructor for utility class.
     */
    private HexUtil() {
    }
}
