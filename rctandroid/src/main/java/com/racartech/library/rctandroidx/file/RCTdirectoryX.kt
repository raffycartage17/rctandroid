package com.racartech.library.rctandroidx.file

import android.os.Build
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.io.UncheckedIOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.Stack
import java.util.stream.Collectors

object RCTdirectoryX {

    @JvmStatic
    fun listFilesInDirectory(dirPath: String): List<String> {
        val dir = File(dirPath)
        return dir.listFiles()?.map { it.absolutePath } ?: emptyList()
    }

    @JvmStatic
    fun clearDirectory(dirPath: String): Boolean {
        val dir = File(dirPath)
        return try {
            if (dir.exists() && dir.isDirectory) {
                FileUtils.cleanDirectory(dir)
                true
            } else false
        } catch (e: IOException) {
            false
        }
    }

    @JvmStatic
    fun isDirectoryEmpty(dirPath: String): Boolean {
        val dir = File(dirPath)
        return dir.isDirectory && dir.list().isNullOrEmpty()
    }

    @JvmStatic
    fun getFreeSpace(dirPath: String): Long {
        val dir = File(dirPath)
        return if (dir.exists()) dir.freeSpace else 0L
    }

    @JvmStatic
    fun deleteFilesInDirectory(dirPath: String): Boolean {
        val dir = File(dirPath)
        return try {
            dir.listFiles()?.forEach {
                if (it.isFile) it.delete()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    @JvmStatic
    fun createDirectory(dirPath: String): Boolean {
        val dir = File(dirPath)
        return if (!dir.exists()) dir.mkdirs() else false
    }

    @JvmStatic
    fun deleteDirectory(dirPath: String): Boolean {
        val dir = File(dirPath)
        return try {
            FileUtils.deleteDirectory(dir)
            true
        } catch (e: IOException) {
            false
        }
    }

    @JvmStatic
    fun copyDirectory(srcPath: String, destPath: String): Boolean {
        return try {
            FileUtils.copyDirectory(File(srcPath), File(destPath))
            true
        } catch (e: IOException) {
            false
        }
    }

    @JvmStatic
    fun moveDirectory(srcPath: String, destPath: String): Boolean {
        return try {
            FileUtils.moveDirectory(File(srcPath), File(destPath))
            true
        } catch (e: IOException) {
            false
        }
    }

    @JvmStatic
    fun getDirectorySize(dirPath: String): Long {
        val dir = File(dirPath)
        return if (dir.exists()) FileUtils.sizeOfDirectory(dir) else 0L
    }

    @JvmStatic
    fun countFilesInDirectory(dirPath: String): Int {
        val dir = File(dirPath)
        return dir.listFiles()?.count { it.isFile } ?: 0
    }

    @JvmStatic
    fun countSubDirectories(dirPath: String): Int {
        val dir = File(dirPath)
        return dir.listFiles()?.count { it.isDirectory } ?: 0
    }

    @JvmStatic
    fun listSubDirectories(dirPath: String): List<String> {
        val dir = File(dirPath)
        return dir.listFiles()?.filter { it.isDirectory }?.map { it.absolutePath } ?: emptyList()
    }

    @JvmStatic
    fun listFilesWithExtension(dirPath: String, extension: String): List<String> {
        val dir = File(dirPath)
        return dir.listFiles { _, name -> name.endsWith(".$extension") }
            ?.map { it.absolutePath } ?: emptyList()
    }

    @JvmStatic
    fun getLastModifiedFile(dirPath: String): String? {
        val dir = File(dirPath)
        return dir.listFiles()
            ?.filter { it.isFile }
            ?.maxByOrNull { it.lastModified() }
            ?.absolutePath
    }

    @JvmStatic
    fun getOldestFile(dirPath: String): String? {
        val dir = File(dirPath)
        return dir.listFiles()
            ?.filter { it.isFile }
            ?.minByOrNull { it.lastModified() }
            ?.absolutePath
    }

    @JvmStatic
    fun getDirectoryName(dirPath: String): String {
        return File(dirPath).name
    }

    @JvmStatic
    fun renameDirectory(dirPath: String, newName: String): Boolean {
        val dir = File(dirPath)
        val newDir = File(dir.parent, newName)
        return dir.exists() && dir.renameTo(newDir)
    }

    @JvmStatic
    fun directoryExists(dirPath: String): Boolean {
        return File(dirPath).exists()
    }

    @JvmStatic
    fun isReadable(dirPath: String): Boolean {
        return File(dirPath).canRead()
    }

    @JvmStatic
    fun isWritable(dirPath: String): Boolean {
        return File(dirPath).canWrite()
    }

    @JvmStatic
    fun touchDirectory(dirPath: String): Boolean {
        val dir = File(dirPath)
        return try {
            dir.setLastModified(System.currentTimeMillis())
        } catch (e: Exception) {
            false
        }
    }

    @JvmStatic
    fun getParentDirectory(dirPath: String): String? {
        return File(dirPath).parent
    }

    @JvmStatic
    fun getFilePathsRecursively(dirPath: String): List<String> {
        val dir = File(dirPath)
        return FileUtils.listFiles(dir, null, true).map { it.absolutePath }
    }

    @JvmStatic
    fun calculateDirectoryChecksum(dirPath: String): Long {
        val dir = File(dirPath)
        return dir.walkTopDown()
            .filter { it.isFile }
            .sumOf { it.length() + it.lastModified() }
    }


    @Throws(IOException::class)
    fun getAllFilesArrayList(
        targetDirectory: String,
        includeFileInAllSubDirectories: Boolean = true
    ): ArrayList<String> {
        val fileList = listAllFilesAndSubDirectoriesArrayList(File(targetDirectory))
        val convertedList = ArrayList<String>()

        for (file in fileList) {
            convertedList.add(file.absolutePath)
        }

        return convertedList
    }

    @Throws(IOException::class)
    fun listAllFilesAndSubDirectoriesArrayList(directory: File): ArrayList<File> {
        val files = ArrayList<File>()

        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            val stack = Stack<File>()
            stack.push(directory)

            while (stack.isNotEmpty()) {
                val dir = stack.pop()
                val dirFiles = dir.listFiles()
                if (dirFiles != null) {
                    for (file in dirFiles) {
                        if (file.isDirectory) {
                            stack.push(file)
                        }
                        files.add(file)
                    }
                }
            }

            files
        } else {
            try {
                val start: Path = directory.toPath()
                Files.walk(start, Int.MAX_VALUE).use { stream ->
                    stream
                        .map(Path::toFile)
                        .sorted()
                        .collect(Collectors.toCollection { ArrayList() })
                }
            } catch (ignored: UncheckedIOException) {
                arrayListOf()
            }
        }
    }



}