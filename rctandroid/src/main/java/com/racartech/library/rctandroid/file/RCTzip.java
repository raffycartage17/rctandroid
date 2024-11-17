package com.racartech.library.rctandroid.file;

import com.racartech.library.rctandroid.array.RCTarray;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class RCTzip {
    private static final int BUFFER_SIZE = 4096;

    public static void compress(String[] file_dir_paths,String zip_file_path) throws IOException{
        ZipUtility.zip(file_dir_paths,zip_file_path);
    }

    public static void compress(String target_path,String zip_file_path) throws IOException{
        ZipUtility.zip(new String[]{target_path},zip_file_path);
    }


    public static void extract(String zip_file_path, String destination_directory) throws IOException {
        File destDir = new File(destination_directory);
        if (!destDir.exists()) {
            boolean mkdir = destDir.mkdir();
        }
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zip_file_path))) {
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            while (entry != null) {
                String filePath = destination_directory+File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    extractFile(zipIn,filePath);
                } else {
                    // if the entry is a directory, make the directory
                    File dir = new File(filePath);
                    boolean mkdirs = dir.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.closeEntry();
            zipIn.close();
        }
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))){
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
            bos.flush();
            bos.close();
        }
    }


    @Deprecated
    public static void compressFilesToZip(String[] source_files_path, String zip_file_path) {
        String[] srcFiles = RCTfile.removeAllNonExistingFiles(source_files_path);
        try{
            byte[] buffer = new byte[1024];
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip_file_path))) {
                for (String srcFile1 : srcFiles) {
                    File srcFile = new File(srcFile1);
                    try (FileInputStream fis = new FileInputStream(srcFile)) {
                        zos.putNextEntry(new ZipEntry(srcFile.getName()));
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                    }
                }
                zos.closeEntry();
                zos.flush();
                zos.close();
            }
        }
        catch (IOException ignored) {
        }
    }


    @Deprecated
    public static void compressFileToZip(String source_file_path, String zip_file_path) {
        if(RCTfile.doesFileExist(source_file_path)){
            String[] srcFiles = {source_file_path};
            try {
                byte[] buffer = new byte[1024];
                try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip_file_path))) {
                    for (String srcFile1 : srcFiles) {
                        File srcFile = new File(srcFile1);
                        try (FileInputStream fis = new FileInputStream(srcFile)) {
                            zos.putNextEntry(new ZipEntry(srcFile.getName()));
                            int length;
                            while ((length = fis.read(buffer)) > 0) {
                                zos.write(buffer, 0, length);
                            }
                            zos.closeEntry();
                        }
                    }
                    zos.closeEntry();
                    zos.flush();
                    zos.close();
                }
            }
            catch (IOException ignored) {
            }
        }
    }

    public static int getFileAndFolderContentCount(String zip_file_path) throws IOException{
        int zip_info_size;
        try (ZipFile zip_info = new ZipFile(zip_file_path)) {
            zip_info_size = zip_info.size();
        }
        return zip_info_size;
    }

    public static int getFileContentCount(String file_path) throws IOException {
        ZipFile zip = new ZipFile(file_path);
        String[] file_entries = new String[getFileAndFolderContentCount(file_path)];
        int current_index = 0;
        for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = e.nextElement();
            if (!entry.isDirectory()) {
                file_entries[current_index] = entry.getName();
                current_index++;
            }
        }
        file_entries = RCTarray.removeAllNull(file_entries);
        return file_entries.length;
    }


    public static String[][] readAllFilesViaUTF8(String file_path) throws IOException {
        ZipFile zip = new ZipFile(file_path);
        String[][] file_contents = new String[getFileContentCount(file_path)][0];
        int current_index = 0;
        for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = e.nextElement();
            if (!entry.isDirectory()){
                file_contents[current_index] = readFileContents(zip.getInputStream(entry),zip.getInputStream(entry));
                current_index++;
            }
        }
        return file_contents;
    }

    public static String[] readFileViaUTF8(String file_path,String file_path_in_zip) throws IOException {
        ZipFile zip = new ZipFile(file_path);
        String[] file_contents = new String[0];
        for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = e.nextElement();
            if (!entry.isDirectory()) {
                if (entry.getName().equals(file_path_in_zip)){
                    file_contents = readFileContents(zip.getInputStream(entry),zip.getInputStream(entry));
                    break;
                }
            }
        }
        return file_contents;
    }

    public static String[] getAllEntriesFileName(String file_path) throws IOException {
        ZipFile zip = new ZipFile(file_path);
        String[] file_entries = new String[getFileAndFolderContentCount(file_path)];
        int current_index = 0;
        for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = e.nextElement();
            if (!entry.isDirectory()) {
                file_entries[current_index] = entry.getName();
                current_index++;
            }
        }
        file_entries = RCTarray.removeAllNull(file_entries);
        return file_entries;
    }




    private static String[] readFileContents(InputStream in_one, InputStream in_two) throws IOException{
        BufferedReader line_counter = new BufferedReader(new InputStreamReader(in_one));
        BufferedReader line_reader = new BufferedReader(new InputStreamReader(in_two));
        int line_count = 0;
        String s;
        while(line_counter.readLine() !=null){
            line_count++;
        }
        String[] its_file_contents = new String[line_count];
        int current_index = 0;

        while((s=line_reader.readLine())!=null){
            its_file_contents[current_index] = s;
            current_index++;
        }
        line_counter.close();
        line_reader.close();
        return its_file_contents;
    }









    public static void compressDirectory(String source_folder, String new_zip_file_path) throws IOException {
        FileOutputStream fos = new FileOutputStream(new_zip_file_path);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(source_folder);
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.flush();
        zipOut.close();
        fos.flush();
        fos.close();
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();
            File[] children = fileToZip.listFiles();
            assert children != null;
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


}

class ZipUtility {
    /**
     * A constants for buffer size used to read/write data
     */
    private static final int BUFFER_SIZE = 4096;
    /**
     * Compresses a list of files to a destination zip file
     * @param listFiles A collection of files and directories
     * @param destZipFile The path of the destination zip file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void zip(List<File> listFiles, String destZipFile) throws FileNotFoundException,
            IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
        for (File file : listFiles) {
            if (file.isDirectory()) {
                zipDirectory(file, file.getName(), zos);
            } else {
                zipFile(file, zos);
            }
        }
        zos.flush();
        zos.close();
    }
    /**
     * Compresses files represented in an array of paths
     * @param files a String array containing file paths
     * @param destZipFile The path of the destination zip file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void zip(String[] files, String destZipFile) throws FileNotFoundException, IOException {
        List<File> listFiles = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            listFiles.add(new File(files[i]));
        }
        zip(listFiles, destZipFile);
    }
    /**
     * Adds a directory to the current zip output stream
     * @param folder the directory to be  added
     * @param parentFolder the path of parent directory
     * @param zos the current zip output stream
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void zipDirectory(File folder, String parentFolder,
                              ZipOutputStream zos) throws FileNotFoundException, IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipDirectory(file, parentFolder + "/" + file.getName(), zos);
                continue;
            }
            zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            long bytesRead = 0;
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = bis.read(bytesIn)) != -1) {
                zos.write(bytesIn, 0, read);
                bytesRead += read;
            }
            zos.closeEntry();
        }
    }
    /**
     * Adds a file to the current zip output stream
     * @param file the file to be added
     * @param zos the current zip output stream
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void zipFile(File file, ZipOutputStream zos)
            throws FileNotFoundException, IOException {
        zos.putNextEntry(new ZipEntry(file.getName()));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));
        long bytesRead = 0;
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = bis.read(bytesIn)) != -1) {
            zos.write(bytesIn, 0, read);
            bytesRead += read;
        }
        zos.closeEntry();
    }
}