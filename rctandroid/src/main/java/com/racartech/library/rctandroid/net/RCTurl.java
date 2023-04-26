package com.racartech.library.rctandroid.net;

import java.net.MalformedURLException;
import java.net.URL;

public class RCTurl {


    public static URL convertStringToUrl(String url_string) {
        URL url = null;
        try{
            url = new URL(url_string);
        }catch(MalformedURLException ignored){}
        return url;
    }
}
