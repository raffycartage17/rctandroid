package com.racartech.library.rctandroid.security;

import com.racartech.library.rctandroid.datatypes.RCTstring;

import java.security.NoSuchAlgorithmException;

public class RCTcustomHashing{


    //REMINDER = Don't Edit this method after September 11,2022 as any
    // alterations in this method might render any programs that use this method broken
    public static String custom_SHA256(String key,String salt){
        try {
            String salt_hash = RCThashing.sha3_256(salt.concat(key));
            String key_hash = RCThashing.sha3_256(key);
            salt_hash = RCTstring.reverse(salt_hash);
            key_hash = RCTstring.reverse(key_hash);
            return RCThashing.sha256(salt_hash.concat(key_hash));
        }catch(NoSuchAlgorithmException ignored){}
        return null;
    }



}
