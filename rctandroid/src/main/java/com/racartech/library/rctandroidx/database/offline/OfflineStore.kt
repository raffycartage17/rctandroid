package com.racartech.library.rctandroidx.database.offline

import android.content.Context
import com.racartech.library.rctandroid.file.RCTfile
import java.io.File

class OfflineStore private constructor(context: Context) {

    private val databaseDirectory: File = File(RCTfile.getDir_IntAppFiles(context), "OfflineStore")

    init {
        createDatabaseDirectory()
    }


    fun isDocument(path: String): Boolean {
        val segments = path.trim().split("/").filter { it.isNotBlank() }
        return segments.size % 2 == 0
    }

    fun isCollection(path: String): Boolean {
        val segments = path.trim().split("/").filter { it.isNotBlank() }
        return segments.size % 2 == 1
    }

    fun getCollection(path: String): String {
        val cleanedPath = path.trim().trim('/')
        val segments = cleanedPath.split("/").filter { it.isNotBlank() }

        return if (isCollection(path)) {
            cleanedPath
        } else if (isDocument(path) && segments.size >= 2) {
            // Remove the document ID to return the collection path
            segments.dropLast(1).joinToString("/")
        } else {
            "" // Invalid path
        }
    }



    fun doesDocumentExist(path: String): Boolean {
        if (!isDocument(path)) return false
        val docFile = File(databaseDirectory, "$path.json")
        return docFile.exists()
    }

    fun doesCollectionExist(path: String): Boolean {
        if (!isCollection(path)) return false
        val collectionDir = File(databaseDirectory, path)
        return collectionDir.exists() && collectionDir.isDirectory
    }








    private fun createDatabaseDirectory() {
        if (!databaseDirectory.exists()) {
            databaseDirectory.mkdirs()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: OfflineStore? = null

        fun getInstance(context: Context): OfflineStore {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: OfflineStore(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
