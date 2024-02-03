package com.capstonelegal.util;
/**
 * Utility class for performing operations with UUIDs.
 * <p>
 * UUID (Universally Unique Identifier) is a 128-bit number used to uniquely identify information in computer systems.
 * The main usage of the UUID is for assigning identifiers with a high probability of uniqueness over space and time,
 * and without the need for central coordination while preventing duplication that are usable across distributed systems.
 * This class provides utility functions related to UUIDs.
 *
 */


import java.util.UUID;

public class UUIDUtil {

    /**
     * Generates a random UUID and returns it as a string.
     * <p>
     * The method uses the 'version 4' variant of UUID, which is based on random numbers.
     * The resulting UUID is a 36 character string representation, including hyphens separating the different components of the UUID.
     *
     * @return A string representation of a randomly generated UUID.
     */
    public static String generateUUID() {
        // Use java.util.UUID to generate a random UUID.
        return UUID.randomUUID().toString();
    }
}
