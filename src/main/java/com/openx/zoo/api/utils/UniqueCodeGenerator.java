package com.openx.zoo.api.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class UniqueCodeGenerator {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789987654321ZYXWVUTSRQPONMLKJIHGFEDCBA";
    private static final int LENGTH = 15;

    public static String generate() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARS.length());
            code.append(CHARS.charAt(index));
        }

        return code.toString();
    }

    public static String uniqueId() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-", "")
                .substring(0, LENGTH)
                .toUpperCase();
    }
}
