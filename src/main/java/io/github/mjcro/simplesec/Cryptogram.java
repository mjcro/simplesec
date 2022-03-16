package io.github.mjcro.simplesec;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public interface Cryptogram {
    /**
     * Constructs new Cryptogram from source bytes where IV located in start of byte array.
     *
     * @param source Source bytes.
     * @param ivLen  Initialization vector length in bytes.
     * @return Cryptogram instance.
     */
    static Cryptogram fromIvDataBytes(byte[] source, int ivLen) {
        Objects.requireNonNull(source, "source");
        byte[] iv = Arrays.copyOfRange(source, 0, ivLen);
        byte[] data = Arrays.copyOfRange(source, ivLen, source.length);

        return create(data, iv);
    }

    /**
     * Creates Cryptogram from HEX data and iv
     *
     * @param dataHEX HEX data
     * @param ivHEX   HEX iv
     * @return Cryptogram instance.
     */
    static Cryptogram fromHex(String dataHEX, final String ivHEX) {
        return create(HexUtil.decode(dataHEX), HexUtil.decode(ivHEX));
    }

    /**
     * Creates Cryptogram from Base64 data and iv
     *
     * @param dataB64 Base64 data
     * @param ivB64   Base64 iv
     * @return Cryptogram instance.
     */
    static Cryptogram fromBase64(String dataB64, String ivB64) {
        return create(Base64.getDecoder().decode(dataB64), Base64.getDecoder().decode(ivB64));
    }

    /**
     * Creates Cryptogram using byte array data.
     *
     * @param data Data.
     * @param iv   Initialization vector.
     * @return Cryptogram instance.
     */
    static Cryptogram create(byte[] data, byte[] iv) {
        return new CryptogramImpl(data, iv);
    }

    /**
     * Returns Initialization vector (salt)
     * This data is not text-safe, so use something like base64 before storage or transfer
     */
    byte[] getIv();

    /**
     * Returns encrypted data
     * This data is not text-safe, so use something like base64 before storage or transfer
     */
    byte[] getData();

    /**
     * @return Initialization vector in a Base64 presentation
     */
    default String getIvB64() {
        return Base64.getEncoder().encodeToString(getIv());
    }

    /**
     * @return Initialization vector in a Hex presentation
     */
    default String getIvHex() {
        return HexUtil.encode(getIv());
    }

    /**
     * @return Encrypted data in a Base64 presentation
     */
    default String getDataB64() {
        return Base64.getEncoder().encodeToString(getData());
    }

    /**
     * @return Encrypted data in a Hex presentation
     */
    default String getDataHex() {
        return HexUtil.encode(getData());
    }

    /**
     * @return Bytes array, that contains both IV (first) and data (second) bytes.
     */
    default byte[] toIvDataBytes() {
        byte[] iv = getIv();
        byte[] data = getData();
        byte[] combined = new byte[iv.length + data.length];

        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(data, 0, combined, iv.length, data.length);
        return combined;
    }
}