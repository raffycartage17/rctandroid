package com.racartech.library.rctandroid.security;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RCThashing {



    public final static int MD5_INT = 0;
    public final static int SHA256_INT = 0;


    public static String md2(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getMD2(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String md5(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getMD5(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha224(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA_224(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha256(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA_256(target_text);
        return GFG.toHexString(encoded_hash);
    }
    public static String sha384(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA_384(target_text);
        return GFG.toHexString(encoded_hash);
    }
    public static String sha512(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA_512(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha512_224(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA_512_224(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha512_256(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA_512_256(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha3_224(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA3_224(target_text);
        return GFG.toHexString(encoded_hash);
    }
    public static String sha3_256(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA3_256(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha3_384(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA3_384(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha3_512(String target_text) throws NoSuchAlgorithmException{
        byte[] encoded_hash = GFG.getSHA3_512(target_text);
        return GFG.toHexString(encoded_hash);
    }

    public static String sha256_CustomSecurity(String target_text,String unique_additional_string) throws NoSuchAlgorithmException{
        String current_hash = sha256(target_text).concat(sha256(unique_additional_string));
        int target_iterations = 0;
        for(int index = 0; index<current_hash.length(); index++){
            target_iterations = target_iterations+((int) current_hash.charAt(index));
        }
        target_iterations = target_iterations-(target_text.concat(unique_additional_string)).length();
        for(int index = 0; index<target_iterations; index++){
            current_hash = sha256(current_hash.concat(unique_additional_string.concat(target_text)));
        }
        return current_hash;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String md5_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha224_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha256_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha384_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha512_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha512_224_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512/224");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha512_256_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512/256");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha3_224_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-224");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha3_256_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-256");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha3_384_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-384");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha3_512_FileHash(String file_path) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-512");
        byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
        return GFG.toHexString(md.digest(file_bytes));
    }






    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha224_MultipleFilesHash(String[] file_paths) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        if(file_paths.length > 0){
            String current_hash = "";
            for (String file_path : file_paths) {
                byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
                String concatenated_hash = current_hash.concat(GFG.toHexString(md.digest(file_bytes)));
                current_hash = sha224(concatenated_hash);
            }
            return current_hash;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha256_MultipleFilesHash(String[] file_paths) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        if(file_paths.length > 0){
            String current_hash = "";
            for (String file_path : file_paths) {
                byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
                String concatenated_hash = current_hash.concat(GFG.toHexString(md.digest(file_bytes)));
                current_hash = sha256(concatenated_hash);
            }
            return current_hash;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha384_MultipleFilesHash(String[] file_paths) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        if(file_paths.length > 0){
            String current_hash = "";
            for (String file_path : file_paths) {
                byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
                String concatenated_hash = current_hash.concat(GFG.toHexString(md.digest(file_bytes)));
                current_hash = sha384(concatenated_hash);
            }
            return current_hash;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sha512_MultipleFilesHash(String[] file_paths) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        if(file_paths.length > 0){
            String current_hash = "";
            for (String file_path : file_paths) {
                byte[] file_bytes = Files.readAllBytes(Paths.get(file_path));
                String concatenated_hash = current_hash.concat(GFG.toHexString(md.digest(file_bytes)));
                current_hash = sha512(concatenated_hash);
            }
            return current_hash;
        }
        return null;
    }

    public static String getFileChecksumSHA256(String file_path) throws IOException, NoSuchAlgorithmException{
        File target_file = new File(file_path);
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
        return getFileChecksum(shaDigest,target_file);
    }


    @SuppressWarnings("empty-statement")
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException{
        //Create byte array to read data in chunks
        try ( //Get file input stream for reading the file content
              FileInputStream fis = new FileInputStream(file)) {
            //Create byte array to read data in chunks
            byte[] byteArray = new byte[1024];
            int bytesCount;
            //Read file data and update in message digest
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            //close the stream; We don't need it now.
        }

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        //return complete hash
        return sb.toString();
    }

}

class GFG {

    public static byte[] getMD2(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD2");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getMD5(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA_224(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    public static byte[] getSHA_256(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA_384(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA_512(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA_512_224(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512/224");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA_512_256(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512/256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }




    public static byte[] getSHA3_224(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-224");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    public static byte[] getSHA3_256(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA3_384(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-384");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA3_512(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA3-512");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }


    public static String toHexString(byte[] hash){
        // Convert byte array into sig_num representation
        BigInteger number = new BigInteger(1, hash);
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}