package com.racartech.library.rctandroid.security;

import java.util.UUID;

public class RCTuuid {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
