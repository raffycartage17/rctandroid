package com.racartech.library.rctandroidx.file

import android.content.Context
import android.os.Environment
import org.apache.commons.io.FileUtils
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.UncheckedIOException
import java.nio.file.Paths

object RCTfileX {

    @JvmStatic
    fun createFile(filePath: String): Boolean {
        return try {
            val newFile = File(filePath)
            if (!newFile.exists()) {
                newFile.createNewFile()
            } else {
                false
            }
        } catch (ignored: IOException) {
            false
        }
    }

    @JvmStatic
    fun createFile(theFile: File): Boolean {
        return try {
            if (!theFile.exists()) {
                theFile.createNewFile()
            } else {
                false
            }
        } catch (ignored: IOException) {
            false
        }
    }


    @JvmStatic
    @Throws(IOException::class)
    fun readFile(file: File): ArrayList<String> {
        val lines = ArrayList<String>()
        BufferedReader(FileReader(file.absolutePath)).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                lines.add(line!!)
            }
        }
        return lines
    }

    @JvmStatic
    @Throws(IOException::class)
    fun readFile(filePath: String): ArrayList<String> {
        val lines = ArrayList<String>()
        BufferedReader(FileReader(filePath)).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                lines.add(line!!)
            }
        }
        return lines
    }

    @JvmStatic
    @Throws(IOException::class)
    fun writeFile(filePath: String, fileContents: ArrayList<String>): Boolean {
        val targetFile = File(filePath)
        return writeFile(targetFile, fileContents)
    }

    @JvmStatic
    @Throws(IOException::class)
    fun writeFile(file: File, fileContents: ArrayList<String>): Boolean {
        if (!file.exists()) return false

        BufferedWriter(OutputStreamWriter(FileOutputStream(file))).use { writer ->
            for (line in fileContents) {
                writer.write(line)
                writer.newLine()
            }
            writer.flush()
        }

        return true
    }

    @JvmStatic
    fun deleteFile(filePath: String): Boolean {
        return deleteFile(File(filePath))
    }

    @JvmStatic
    fun deleteFile(file: File): Boolean {
        return if (file.exists()) {
            try {
                file.delete()
            } catch (e: UncheckedIOException) {
                false
            }
        } else {
            false
        }
    }


    @JvmStatic
    fun getDirIntAppFiles(appContext: Context): String {
        return appContext.filesDir.absolutePath
    }

    @JvmStatic
    fun getDirIntAppCache(appContext: Context): String {
        return appContext.cacheDir.absolutePath
    }

    @JvmStatic
    fun getDirIntAppData(appContext: Context): String {
        return appContext.dataDir.absolutePath
    }

    @JvmStatic
    fun getDirIntAppCodeCache(appContext: Context): String {
        return appContext.codeCacheDir.absolutePath
    }

    @JvmStatic
    fun getDirExtAppFiles(appContext: Context): String? {
        return appContext.getExternalFilesDir(null)?.absolutePath
    }

    @JvmStatic
    fun getDirExtAppCache(appContext: Context): String? {
        return appContext.externalCacheDir?.absolutePath
    }

    @JvmStatic
    fun getDirExternalStorageRoot(): String {
        return Environment.getExternalStorageDirectory().absolutePath
    }

    @JvmStatic
    fun getFileName(targetPath: String): String {
        return Paths.get(targetPath).fileName.toString()
    }

    @JvmStatic
    fun renameFile(filePath: String, newName: String): Boolean {
        val originalFile = File(filePath)
        if (!originalFile.exists()) return false

        val newFile = File(originalFile.parent, newName)
        return originalFile.renameTo(newFile)
    }


    @JvmStatic
    @Throws(IOException::class)
    fun copyFileToDirectory(filePath: String, toDirectory: String) {
        val sourceFile = File(filePath)
        val targetDir = File(toDirectory)

        if (!sourceFile.exists()) {
            throw IOException("Source file does not exist: $filePath")
        }

        if (!targetDir.exists()) {
            targetDir.mkdirs()
        }

        FileUtils.copyFileToDirectory(sourceFile, targetDir)
    }

    @JvmStatic
    @Throws(IOException::class)
    fun moveFileToDirectory(filePath: String, toDirectory: String) {
        val sourceFile = File(filePath)
        val targetDir = File(toDirectory)

        if (!sourceFile.exists()) {
            throw IOException("Source file does not exist: $filePath")
        }

        if (!targetDir.exists()) {
            targetDir.mkdirs()
        }

        FileUtils.moveFileToDirectory(sourceFile, targetDir, true)
    }

    @JvmStatic
    @Throws(IOException::class)
    fun appendToFile(filePath: String, line: String): Boolean {
        val file = File(filePath)
        if (!file.exists()) return false

        BufferedWriter(OutputStreamWriter(FileOutputStream(file, true))).use { writer ->
            writer.write(line)
            writer.newLine()
            writer.flush()
        }
        return true
    }
    @JvmStatic
    fun fileExists(filePath: String): Boolean {
        return File(filePath).exists()
    }
    @JvmStatic
    fun createDirectory(dirPath: String): Boolean {
        val dir = File(dirPath)
        return if (!dir.exists()) dir.mkdirs() else false
    }

    @JvmStatic
    fun getFileExtension(filePath: String): String {
        return File(filePath).extension
    }

    @JvmStatic
    fun getFileSize(filePath: String): Long {
        val file = File(filePath)
        return if (file.exists()) file.length() else 0L
    }

    @JvmStatic
    fun isFileReadable(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists() && file.canRead()
    }

    @JvmStatic
    fun isFileWritable(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists() && file.canWrite()
    }

    @JvmStatic
    fun getLastModified(filePath: String): Long {
        val file = File(filePath)
        return if (file.exists()) file.lastModified() else 0L
    }

    @JvmStatic
    fun getMimeType(filePath: String): String {
        val ext = getFileExtension(filePath)
        return android.webkit.MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(ext.lowercase()) ?: "application/octet-stream"
    }

    @JvmStatic
    fun readFileAsSingleLineString(filePath: String): String {
        val file = File(filePath)
        return if (file.exists()) file.readText() else ""
    }

    @JvmStatic
    fun writeFile(filePath: String, content: String): Boolean {
        return try {
            File(filePath).writeText(content)
            true
        } catch (e: IOException) {
            false
        }
    }

    @JvmStatic
    fun fileContainsText(filePath: String, keyword: String): Boolean {
        return readFileAsSingleLineString(filePath).contains(keyword)
    }

    @JvmStatic
    fun getFilePathWithoutExtension(filePath: String): String {
        val file = File(filePath)
        return file.parent + File.separator + file.nameWithoutExtension
    }


    @JvmStatic
    fun isPathAFile(path: String): Boolean {
        val file = File(path)
        return file.isFile
    }

    @JvmStatic
    fun isPathADirectory(path: String): Boolean {
        val file = File(path)
        return file.isDirectory
    }
    






}