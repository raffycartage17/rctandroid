package com.racartech.library.rctandroid.datatypes;

import com.racartech.library.rctandroid.array.RCTarray;
import com.racartech.library.rctandroid.math.RCTrandom;

public class RCTstring{


    public final static char[] CHARACTERS = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '0','1','2','3','4','5','6','7','8','9'
    };

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


    public static String randomString(int min_length,int max_length){
        int selected_length = RCTrandom.fromTo(min_length,max_length);
        char[] random_char_array = new char[selected_length];
        for(int index = 0; index<selected_length; index++){
            random_char_array[index] = CHARACTERS[RCTrandom.fromTo(0,CHARACTERS.length-1)];
        }
        return new String(random_char_array);
    }



    public static String deletePart(String target_string,int from,int to){
        char[] chared_string = target_string.toCharArray();
        chared_string = RCTarray.deletePart(chared_string,from,to);
        return RCTarray.concatArrayToString(chared_string,"");
    }
    public static String addChar(String target_string,char additional_char){
        char[] target_string_char = target_string.toCharArray();
        target_string_char = RCTarray.add(target_string_char,additional_char);
        return RCTarray.concatArrayToString(target_string_char,"");
    }

    public static String addChar(String target_string,char[] additional_char){
        char[] target_string_char = target_string.toCharArray();
        target_string_char = RCTarray.merge(target_string_char,additional_char);
        return RCTarray.concatArrayToString(target_string_char,"");
    }

    public static String replaceChar(String target_string,int target_char_index,char replacement_char){
        if(target_char_index >= 0 && target_char_index <target_string.length()) {
            char[] target_string_char = target_string.toCharArray();
            target_string_char[target_char_index] = replacement_char;
            return RCTarray.concatArrayToString(target_string_char,"");
        }else{
            return target_string;
        }
    }



    public static String substring(String target_string, int length){
        return target_string.substring(0,length);
    }
    public static String substring(String target_string, int length,String additonal){
        return target_string.substring(0,length).concat(additonal);
    }


}
