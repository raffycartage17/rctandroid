package com.racartech.library.rctandroid.util;

import android.os.Build;

import java.io.File;
import java.util.Comparator;

public class RCTcomparator{



    public static Comparator<File> getFileComparator_OldestFirst_FileFirst() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return -1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return 1;
                    } else {
                        long lastModified1 = f1.lastModified();
                        long lastModified2 = f2.lastModified();
                        int cmp = Long.compare(lastModified1, lastModified2);
                        if (cmp == 0) {
                            cmp = f1.getName().compareToIgnoreCase(f2.getName());
                        }
                        return cmp;
                    }
                }
            };
        } else {
            return Comparator.comparing(File::isDirectory)
                    .thenComparing(File::lastModified)
                    .thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER);
        }

    }

    public static Comparator<File> getFileComparator_OldestFirst_DirFirst() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return 1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return -1;
                    } else {
                        long lastModified1 = f1.lastModified();
                        long lastModified2 = f2.lastModified();
                        int cmp = Long.compare(lastModified1, lastModified2);
                        if (cmp == 0) {
                            cmp = f1.getName().compareToIgnoreCase(f2.getName());
                        }
                        return cmp;
                    }
                }
            };
        } else {
            return Comparator.comparing(File::isDirectory, Comparator.reverseOrder())
                    .thenComparing(File::lastModified)
                    .thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER);
        }

    }


    public static Comparator<File> getFileComparator_NewestFirst_FileFirst() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return 1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return -1;
                    } else {
                        long lastModified1 = f1.lastModified();
                        long lastModified2 = f2.lastModified();
                        int cmp = Long.compare(lastModified2, lastModified1);
                        if (cmp == 0) {
                            cmp = f1.getName().compareToIgnoreCase(f2.getName());
                        }
                        return cmp;
                    }
                }
            };
        } else {
            return Comparator.comparing(File::isDirectory)
                    .thenComparing(File::lastModified, Comparator.reverseOrder())
                    .thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER);
        }

    }

    public static Comparator<File> getFileComparator_NewestFirst_DirFirst() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return -1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return 1;
                    } else {
                        long lastModified1 = f1.lastModified();
                        long lastModified2 = f2.lastModified();
                        int cmp = Long.compare(lastModified2, lastModified1);
                        if (cmp == 0) {
                            cmp = f1.getName().compareToIgnoreCase(f2.getName());
                        }
                        return cmp;
                    }
                }
            };
        } else {
            return Comparator.comparing(File::isDirectory, Comparator.reverseOrder())
                    .thenComparing(File::lastModified, Comparator.reverseOrder())
                    .thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER);
        }

    }


    public static Comparator<File> getFileComparator_CaseInsensitive_DirectoryFirst() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    String name1 = f1.getName().toLowerCase();
                    String name2 = f2.getName().toLowerCase();
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return -1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return 1;
                    } else {
                        return name1.compareTo(name2);
                    }
                }
            };
        } else {
            return Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(File::isDirectory, Comparator.reverseOrder());
        }

    }

    public static Comparator<File> getFileComparator_CaseSensitive_DirectoryFirst() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    String name1 = f1.getName();
                    String name2 = f2.getName();
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return -1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return 1;
                    } else {
                        return name1.compareTo(name2);
                    }
                }
            };
        } else {
            return Comparator.comparing(File::getName)
                    .thenComparing(File::isDirectory, Comparator.reverseOrder());
        }

    }

    public static Comparator<File> getFileComparator_CaseInsensitive_FileFirst() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    String name1 = f1.getName().toLowerCase();
                    String name2 = f2.getName().toLowerCase();
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return 1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return -1;
                    } else {
                        return name1.compareTo(name2);
                    }
                }
            };
        } else {
            return Comparator.comparing(File::isDirectory, Comparator.reverseOrder())
                    .thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER);
        }

    }



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
