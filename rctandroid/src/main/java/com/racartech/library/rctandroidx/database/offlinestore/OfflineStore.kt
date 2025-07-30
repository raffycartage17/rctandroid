package com.racartech.library.rctandroidx.database.offlinestore

import android.content.Context
import com.racartech.library.rctandroid.file.RCTdirectory
import com.racartech.library.rctandroid.file.RCTfile
import com.racartech.library.rctandroidx.file.RCTfileX
import org.json.JSONObject
import java.io.File

class OfflineStore private constructor(context: Context) {

    private val databaseDirectory: File = File(RCTfile.getDir_IntAppFiles(context), "OfflineStore")

    init {
        createDatabaseDirectory()
    }


    fun isDocument(path: String): Boolean {
        return RCTfileX.isPathAFile(getPathAbsoluteFilePath(path))
    }

    fun isDirectory(path: String): Boolean {
        return RCTfileX.isPathADirectory(getPathAbsoluteFilePath(path))
    }

    fun getParentDirectory(path : String) : String{
        if(isDirectory(path)) {
            return path
        }else{
            return path.substring(0,path.lastIndexOf("/"))
        }
    }



    fun doesDocumentExist(path: String): Boolean {
        if (!isDocument(path)) return false
        val docFile = File(getPathAbsoluteFilePath(path))
        return docFile.exists()
    }

    fun doesDirectoryExist(path: String): Boolean {
        if (!isDirectory(path)) return false
        val collectionDir = File(getPathAbsoluteFilePath(path))
        return collectionDir.exists() && collectionDir.isDirectory
    }

    fun createDocument(documentPath : String) : Boolean{
        if(!doesDocumentExist(documentPath)) {
            RCTdirectory.createDirectory(getPathAbsoluteFilePath(getParentDirectory(documentPath)))
            if(doesDirectoryExist(getParentDirectory(documentPath))){
                var absolutePath = getPathAbsoluteFilePath(documentPath)
                var initialContents : String = JSONObject().toString()
                RCTfileX.createFile(absolutePath)
                RCTfileX.writeFile(absolutePath,initialContents)
                return true
            }
        }
        return false
    }

    fun putField(documentPath : String, fieldName : String, fieldValue : Any) : Boolean{
        if(doesDocumentExist(documentPath)){
            var absolutePath = getPathAbsoluteFilePath(documentPath)
            var json = getDocumentAsJSON(documentPath)
            json.put(fieldName,fieldValue)
            RCTfileX.writeFile(absolutePath,json.toString())
            return true
        }
        return false
    }

    fun putFields(documentPath: String, fields: HashMap<String, Any>): Boolean {
        if (doesDocumentExist(documentPath)) {
            val absolutePath = getPathAbsoluteFilePath(documentPath)
            val json = getDocumentAsJSON(documentPath)

            for ((key, value) in fields) {
                json.put(key, value)
            }

            RCTfileX.writeFile(absolutePath, json.toString())
            return true
        }
        return false
    }

    fun readField(documentPath : String, fieldName : String): Any?{
        if(doesDocumentExist(documentPath)){
            var json = getDocumentAsJSON(documentPath)
            return json.get(fieldName)
        }
        return null
    }

    fun readField(documentPath: String, fieldNames: ArrayList<String>): HashMap<String, Any> {
        val result = HashMap<String, Any>()

        if (doesDocumentExist(documentPath)) {
            val json = getDocumentAsJSON(documentPath)

            for (fieldName in fieldNames) {
                if (json.has(fieldName)) {
                    result[fieldName] = json.get(fieldName)
                }
            }
        }

        return result
    }

    fun deleteField(documentPath : String, fieldName : String) : Any?{
        if(doesDocumentExist(documentPath)){
            val absolutePath = getPathAbsoluteFilePath(documentPath)
            val json = getDocumentAsJSON(documentPath)
            val fieldValue = json.remove(fieldName)
            RCTfileX.writeFile(absolutePath, json.toString())
            return fieldValue
        }
        return null
    }

    fun deleteField(documentPath: String, fieldNames: ArrayList<String>): HashMap<String, Any> {
        val deletedFields = HashMap<String, Any>()

        if (doesDocumentExist(documentPath)) {
            val absolutePath = getPathAbsoluteFilePath(documentPath)
            val json = getDocumentAsJSON(documentPath)

            for (fieldName in fieldNames) {
                if (json.has(fieldName)) {
                    val removed = json.remove(fieldName)
                    if (removed != null) {
                        deletedFields[fieldName] = removed
                    }
                }
            }

            RCTfileX.writeFile(absolutePath, json.toString())
        }

        return deletedFields
    }







    private fun doesFieldExist(jsonObject: JSONObject, fieldName: String): Boolean {
        return jsonObject.has(fieldName)
    }


    private fun getDocumentAsJSON(documentPath : String) : JSONObject{
        //Check on parent method if document exists
        var absolutePath = getPathAbsoluteFilePath(documentPath)
        return JSONObject(RCTfileX.readFileAsSingleLineString(absolutePath))

    }








    fun getPathAbsoluteFilePath(path : String) : String{
        return databaseDirectory.absolutePath+"/"+path;
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
