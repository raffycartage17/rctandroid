package com.racartech.library.rctandroid.net;

import android.util.Patterns;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class RCTurl {





    public static URL convertUriToUrl(String uriString) {
        try {
            // Create a URI object from the URI string
            java.net.URI uri = new java.net.URI(uriString);

            // Convert the URI object to a URL object
            URL url = uri.toURL();

            return url;
        } catch (MalformedURLException | java.net.URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static URL convertStringToUrl(String url_string) {
        URL url = null;
        try{
            url = new URL(url_string);
        }catch(MalformedURLException ignored){}
        return url;
    }

    public static boolean isStringURL(String input) {
        return Patterns.WEB_URL.matcher(input).matches();
    }


    public static boolean isUrlValid(URL url) {
        try {
            url.toURI();
            return true;
        } catch (URISyntaxException | NullPointerException e) {
            return false;
        }
    }

    public static String getBaseUrl(URL url) {
        String baseUrl = url.getProtocol() + "://" + url.getHost();
        if (url.getPort() != -1) {
            baseUrl += ":" + url.getPort();
        }
        return baseUrl;
    }

    public static String getUrlProtocol(URL url) {
        return url.getProtocol();
    }

    public static String getUrlHost(URL url) {
        return url.getHost();
    }

    public static int getUrlPort(URL url) {
        return url.getPort();
    }

    public static String getUrlPath(URL url) {
        return url.getPath();
    }

    public static String getUrlQuery(URL url) {
        return url.getQuery();
    }

    public static String getUrlFragment(URL url) {
        return url.getRef();
    }

    public static URL appendPathToUrl(URL url, String path) throws MalformedURLException {
        String currentPath = url.getPath();
        if (!currentPath.endsWith("/")) {
            currentPath += "/";
        }
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        URL newUrl = new URL(url.getProtocol(), url.getHost(), url.getPort(), currentPath + path);
        return newUrl;
    }

    public static URL addQueryParameter(URL url, String parameter, String value) throws MalformedURLException {
        String currentQuery = url.getQuery();
        if (currentQuery == null) {
            currentQuery = "";
        } else {
            currentQuery += "&";
        }
        currentQuery += parameter + "=" + value;
        URL newUrl = new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getPath() + "?" + currentQuery);
        return newUrl;
    }




}
