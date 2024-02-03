package com.capstonelegal.util;

/**
 * Utility class for performing hashing operations.
 * <p>
 * Hashing is the process of converting an input of any length into a fixed size string of text,
 * using a mathematical function. This class uses the SHA-512 (Secure Hash Algorithm 512-bit) which
 * is part of SHA-2 set of cryptographic hash functions, designed by the National Security Agency (NSA).
 * It's generally used to verify data integrity.
 *
 */


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {

    private static final String SHA_512 = "SHA-512";  // Hashing algorithm to be used

    /**
     * Computes the SHA-512 hash of the input text, and returns the hash encoded as a Base64 string.
     * <p>
     * This function takes as input a string, computes its SHA-512 hash, and then encodes the hash
     * in Base64. Base64 encoding is commonly used when there is a need to encode binary data,
     * especially when that data needs to be stored and transferred over media that are designed to
     * deal with text. This encoding helps to ensure that the data remains intact without modification
     * during transport.
     *
     * @param text The input string to be hashed.
     * @return The Base64-encoded hash of the input string.
     * @throws RuntimeException If the SHA-512 algorithm is not available.
     */
    public static String hash(String text) {
        MessageDigest digest;
        try {
            // Attempt to get an instance of the SHA-512 message digest
            digest = MessageDigest.getInstance(SHA_512);
        } catch (NoSuchAlgorithmException e) {
            // If the SHA-512 algorithm is not available, throw an exception
            throw new RuntimeException("Failed to get SHA-512 algorithm", e);
        }

        // Compute the SHA-512 hash of the input string
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

        // Return the Base64-encoded hash
        return Base64.getEncoder().encodeToString(hash);
    }
}
