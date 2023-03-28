package com.racartech.library.rctandroid.datatypes;

import com.racartech.library.rctandroid.array.RCTarray;

public class RCTstring{
    public static String reverse(String target_string){
        char[] target_string_char = target_string.toCharArray();
        RCTarray.reverse(target_string_char);
        return RCTarray.concatArrayToString(target_string_char,"");
    }

    public static int[] getCharSequenceIndexMatch(String key_string,String root_string){
        int index_of = root_string.indexOf(key_string);
        if(index_of >= 0){
            int[] position = new int[2];
            position[0] = index_of;
            position[1] = index_of+key_string.length()-1;
            return position;
        }else{
            return null;
        }
    }
}
