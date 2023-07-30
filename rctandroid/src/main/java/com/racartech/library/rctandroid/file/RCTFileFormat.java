package com.racartech.library.rctandroid.file;

import com.racartech.library.rctandroid.array.RCTarray;

public class RCTFileFormat{

    public static final String[] FILE_EXTENSION_IMAGE = {
            "png",
            "jpg",
            "jpeg",
            "raw",
            "gif",
            "tiff"
    };


    public static final String[] FILE_EXTENSION_VIDEO = {
            "mp4",
            "mov",
            "avi",
            "wmv",
            "flv",
            "mkv",
            "webm"
    };

    public static final String[] FILE_EXTENSION_AUDIO = {
            "mp3",
            "wav",
            "pcm",
            "aiff",
            "aac",
            "ogg",
            "wma",
            "flac",
            "alac"
    };


    public static final String[] FILE_EXTENSION_TEXT = {
            "txt",
            "text",
            "java",
            "js",
            "cs",
            "py",
            "cpp"
    };







    public static final String[] FILE_EXTENSION_MSWORD = {
            "doc",
            "docm",
            "docx",
            "dot",
            "dotx",
            "rtf"
    };

    public static final String[] FILE_EXTENSION_MSPOWERPOINT = {
            "pot",
            "potm",
            "potx",
            "ppam",
            "pps",
            "ppsm",
            "ppsx",
            "ppt",
            "pptm",
            "pptx",
            "sldm",
            "sldx"
    };

    public static final String[] FILE_EXTENSION_MSACCESS = {
            "accdb",
            "accde",
            "accdr",
            "accdt",
            "mdb"
    };

    public static final String[] FILE_EXTENSION_MSEXCEL = {
            "xla",
            "xlam",
            "xll",
            "xlm",
            "xls",
            "xlsm",
            "xlsx",
            "xlt",
            "xltm",
            "xltx"
    };

    public static final String[] FILE_EXTENSION_COMPRESSED = {
            "zip",
            "rar",
            "7z",
            "tar",
            "gz",
            "jar",
            "cab",
            "dmg",
            "pea",
            "wim",
            "xz",
            "gzip",
            "bzip2"
    };

    public static final String[] FILE_EXTENSION_PDF = {
            "pdf"
    };



    public static final int IMAGE = 0;
    public static final int VIDEO = 1;
    public static final int AUDIO = 2;
    public static final int MSWORD = 3;
    public static final int MSEXCEL = 4;
    public static final int MSPOWERPOINT = 5;
    public static final int MSACCESS = 6;
    public static final int PDF = 7;
    public static final int COMPRESSED = 8;
    public static final int TEXT = 9;



    public static int getExtensionType(String file_extension){
        file_extension = file_extension.toLowerCase();
        if(RCTarray.contains(FILE_EXTENSION_IMAGE,file_extension,false)){
            return IMAGE;
        }
        if(RCTarray.contains(FILE_EXTENSION_VIDEO,file_extension,false)){
            return VIDEO;
        }
        if(RCTarray.contains(FILE_EXTENSION_AUDIO,file_extension,false)){
            return AUDIO;
        }
        if(RCTarray.contains(FILE_EXTENSION_MSWORD,file_extension,false)){
            return MSWORD;
        }
        if(RCTarray.contains(FILE_EXTENSION_MSEXCEL,file_extension,false)){
            return MSEXCEL;
        }
        if(RCTarray.contains(FILE_EXTENSION_MSACCESS,file_extension,false)){
            return MSACCESS;
        }
        if(RCTarray.contains(FILE_EXTENSION_MSPOWERPOINT,file_extension,false)){
            return MSPOWERPOINT;
        }
        if(RCTarray.contains(FILE_EXTENSION_COMPRESSED,file_extension,false)){
            return COMPRESSED;
        }
        if(RCTarray.contains(FILE_EXTENSION_PDF,file_extension,false)){
            return PDF;
        }
        if(RCTarray.contains(FILE_EXTENSION_TEXT,file_extension,false)){
            return TEXT;
        }
        return -1;
    }











}
