package com.racartech.library.rctandroid.uuid;

import java.util.UUID;

public class RCTuuid {

    public static String generateID(){
        return UUID.randomUUID().toString();
    }
}
