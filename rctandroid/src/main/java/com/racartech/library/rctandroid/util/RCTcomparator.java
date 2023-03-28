package com.racartech.library.rctandroid.util;

import java.util.Comparator;

public class RCTcomparator{

    public static Comparator<String> getComparator_CASE_INSENSITIVE() {
        Comparator<String> ignoreCaseComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        };
        return ignoreCaseComparator;
    }

    public static Comparator<String> getComparator_CASE_SENSITIVE() {
        Comparator<String> caseSensitiveComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        return caseSensitiveComparator;
    }

    public static Comparator<String> getComparator_SMALlCAPSFIRST_BIGCAPSLAST() {
        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                char firstChar1 = s1.charAt(0);
                char firstChar2 = s2.charAt(0);

                if (Character.isLowerCase(firstChar1) && Character.isUpperCase(firstChar2)) {
                    return -1;
                } else if (Character.isUpperCase(firstChar1) && Character.isLowerCase(firstChar2)) {
                    return 1;
                } else {
                    int result = Character.compare(firstChar1, firstChar2);
                    if (result != 0) {
                        return result;
                    } else {
                        return s1.compareToIgnoreCase(s2);
                    }
                }
            }
        };
        return customComparator;
    }

    public static Comparator<String> getComparator_BIGCAPSFIRST_SMALLCAPSLAST() {
        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                char firstChar1 = s1.charAt(0);
                char firstChar2 = s2.charAt(0);

                if (Character.isUpperCase(firstChar1) && Character.isLowerCase(firstChar2)) {
                    return -1;
                } else if (Character.isLowerCase(firstChar1) && Character.isUpperCase(firstChar2)) {
                    return 1;
                } else {
                    int result = Character.compare(firstChar1, firstChar2);
                    if (result != 0) {
                        return result;
                    } else {
                        return s1.compareToIgnoreCase(s2);
                    }
                }
            }
        };
        return customComparator;
    }






}
