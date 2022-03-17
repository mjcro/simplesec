package io.github.mjcro.simplesec.hash;

import io.github.mjcro.simplesec.Hasher;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class DigestHasher implements Hasher {
    private final int size;
    private final String digest;

    DigestHasher(final int size, final String digest) {
        this.size = size;
        this.digest = digest;

        // Creating digest to check that digest name is correct
        getDigest();
    }

    MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance(this.digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] hash(InputStream data) throws IOException {
        MessageDigest md = getDigest();
        byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        while (read > -1) {
            md.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }
        return md.digest();
    }

    @Override
    public byte[] hash(byte[] data) {
        MessageDigest md = getDigest();
        md.update(data);
        return md.digest();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return this.digest + " hasher";
    }
}