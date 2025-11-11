package com.racartech.library.rctandroidx.database.offline

import android.content.Context
import com.racartech.library.rctandroid.file.RCTfile
import org.json.JSONObject
import java.io.File

class OfflineStore private constructor(context: Context) {

    private val databaseDirectory = File(RCTfile.getDir_IntAppFiles(context), "OfflineStore")


    private val cache = mutableMapOf<String, JSONObject>()

    init {
        if (!databaseDirectory.exists()) databaseDirectory.mkdirs()
    }



    fun readCollection(collection: String): List<Map<String, Any>> {
        val documents = listDocuments(collection)
        return documents.mapNotNull { getAllFields(collection, it) }
    }

    @Synchronized
    fun updateCollectionName(oldName: String, newName: String): Boolean {
        val oldDir = File(databaseDirectory, oldName)
        val newDir = File(databaseDirectory, newName)
        if (!oldDir.exists() || newDir.exists()) return false
        return oldDir.renameTo(newDir)
    }


    fun collectionExists(collection: String): Boolean {
        return File(databaseDirectory, collection).exists()
    }

    fun readDocument(collection: String, document: String): Map<String, Any>? {
        return getAllFields(collection, document)
    }

    @Synchronized
    fun updateDocument(collection: String, document: String, newData: Map<String, Any>) {
        val json = JSONObject(newData)
        saveDocument(collection, document, json)
    }

    fun documentExists(collection: String, document: String): Boolean {
        return getDocumentFile(collection, document).exists()
    }

    @Synchronized
    fun renameDocument(collection: String, oldName: String, newName: String): Boolean {
        val oldFile = getDocumentFile(collection, oldName)
        val newFile = getDocumentFile(collection, newName)
        if (!oldFile.exists() || newFile.exists()) return false
        return oldFile.renameTo(newFile)
    }

    @Synchronized
    fun updateField(collection: String, document: String, key: String, newValue: Any) {
        val json = loadDocument(collection, document)
        if (json.has(key)) {
            json.put(key, newValue)
            saveDocument(collection, document, json)
        }
    }

    @Synchronized
    fun deleteField(collection: String, document: String, key: String): Boolean {
        val json = loadDocument(collection, document)
        if (json.has(key)) {
            json.remove(key)
            saveDocument(collection, document, json)
            return true
        }
        return false
    }

    fun fieldExists(collection: String, document: String, key: String): Boolean {
        val json = loadDocument(collection, document)
        return json.has(key)
    }


    @Synchronized
    fun createCollection(collection: String) {
        val target = File(databaseDirectory, collection)
        if (!target.exists()) target.mkdirs()
    }

    @Synchronized
    fun deleteCollection(collection: String) {
        File(databaseDirectory, collection).deleteRecursively()
    }

    fun listCollections(): List<String> =
        databaseDirectory.list()?.toList() ?: emptyList()


    private fun getDocumentFile(collection: String, document: String) =
        File(databaseDirectory, "$collection/$document.json")

    @Synchronized
    fun createDocument(collection: String, document: String, data: Map<String, Any>? = null) {
        createCollection(collection)
        val file = getDocumentFile(collection, document)

        if (!file.exists()) file.createNewFile()

        val json = JSONObject(data ?: emptyMap<String, Any>())
        file.writeText(json.toString())
        cache["$collection/$document"] = json
    }

    @Synchronized
    fun deleteDocument(collection: String, document: String) {
        getDocumentFile(collection, document).delete()
        cache.remove("$collection/$document")
    }


    fun listDocuments(collection: String): List<String> {
        val dir = File(databaseDirectory, collection)
        return dir.listFiles()?.map { it.nameWithoutExtension } ?: emptyList()
    }


    @Synchronized
    fun addField(collection: String, document: String, key: String, value: Any) {
        val json = loadDocument(collection, document)
        json.put(key, value)
        saveDocument(collection, document, json)
    }

    fun readField(collection: String, document: String, key: String): Any? {
        val json = loadDocument(collection, document)
        return if (json.has(key)) json.get(key) else null
    }

    fun getAllFields(collection: String, document: String): Map<String, Any>? {
        val json = loadDocument(collection, document)
        return json.toMap()
    }


    inline fun <reified T> readAsObject(collection: String, document: String, mapper: (Map<String, Any>) -> T): T? {
        val data = getAllFields(collection, document) ?: return null
        return mapper(data)
    }


    private fun loadDocument(collection: String, document: String): JSONObject {
        val key = "$collection/$document"

        cache[key]?.let { return it }

        val file = getDocumentFile(collection, document)
        if (!file.exists()) {
            createDocument(collection, document)
            return JSONObject()
        }

        val json = try {
            JSONObject(file.readText())
        } catch (_: Exception) {
            JSONObject()
        }

        cache[key] = json
        return json
    }

    @Synchronized
    private fun saveDocument(collection: String, document: String, json: JSONObject) {
        val file = getDocumentFile(collection, document)
        val tmp = File(file.parent, "${file.name}.tmp")

        try {
            tmp.writeText(json.toString())
            if (tmp.renameTo(file)) {
                cache["$collection/$document"] = json
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (tmp.exists()) tmp.delete()
        }
    }


    companion object {
        @Volatile private var INSTANCE: OfflineStore? = null

        fun getInstance(context: Context): OfflineStore =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: OfflineStore(context.applicationContext).also { INSTANCE = it }
            }
    }
}

// --- Extension to convert JSONObject to Map<String, Any> ---
private fun JSONObject.toMap(): Map<String, Any> =
    keys().asSequence().associateWith { this.get(it) }
