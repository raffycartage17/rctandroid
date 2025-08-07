package com.racartech.library.rctandroidx.database.offlinestore

import android.content.Context
import com.racartech.library.rctandroid.file.RCTdirectory
import com.racartech.library.rctandroid.file.RCTfile
import com.racartech.library.rctandroidx.file.RCTdirectoryX
import com.racartech.library.rctandroidx.file.RCTfileX
import org.json.JSONObject
import java.io.File

class OfflineStore private constructor(context: Context) {

    private val databaseDirectory: File = File(RCTfile.getDir_IntAppFiles(context), "OfflineStore")

    init {
        createDatabaseDirectory()
    }


//    fun isDocument(path: String): Boolean {
//        return RCTfileX.isPathAFile(getDocumentPathAbsoluteFilePath(path))
//    }
//
//    fun isDirectory(path: String): Boolean {
//        return RCTfileX.isPathADirectory(getDirectoryPathAbsoluteFilePath(path))
//    }
//
//    fun getParentDirectory(path : String) : String{
//        if(isDirectory(path)) {
//            return path
//        }else{
//            return path.substring(0,path.lastIndexOf("/"))
//        }
//    }
//
//
//
//    fun doesDocumentExist(path: String): Boolean {
//        if (!isDocument(path)) return false
//        val docFile = File(getDocumentPathAbsoluteFilePath(path))
//        return docFile.exists()
//    }
//
//    fun doesDirectoryExist(path: String): Boolean {
//        if (!isDirectory(path)) return false
//        val collectionDir = File(getDocumentPathAbsoluteFilePath(path))
//        return collectionDir.exists() && collectionDir.isDirectory
//    }
//
//    @Synchronized
//    fun createDocument(documentPath : String) : Boolean{
//        if(!doesDocumentExist(documentPath)) {
//            RCTdirectory.createDirectory(getDirectoryPathAbsoluteFilePath(getParentDirectory(documentPath)))
//            if(doesDirectoryExist(getParentDirectory(documentPath))){
//                var absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
//                var initialContents : String = JSONObject().toString()
//                RCTfileX.createFile(absolutePath)
//                RCTfileX.writeFile(absolutePath,initialContents)
//                return true
//            }
//        }
//        return false
//    }
//
//    @Synchronized
//    fun putField(documentPath : String, fieldName : String, fieldValue : Any) : Boolean{
//        if(doesDocumentExist(documentPath)){
//            var absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
//            var json = getDocumentAsJSON(documentPath)
//            json.put(fieldName,fieldValue)
//            RCTfileX.writeFile(absolutePath,json.toString())
//            return true
//        }
//        return false
//    }
//
//    @Synchronized
//    fun putFields(documentPath: String, fields: HashMap<String, Any>): Boolean {
//        if (doesDocumentExist(documentPath)) {
//            val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
//            val json = getDocumentAsJSON(documentPath)
//
//            for ((key, value) in fields) {
//                json.put(key, value)
//            }
//
//            RCTfileX.writeFile(absolutePath, json.toString())
//            return true
//        }
//        return false
//    }
//
//    @Synchronized
//    fun readField(documentPath : String, fieldName : String): Any?{
//        if(doesDocumentExist(documentPath)){
//            var json = getDocumentAsJSON(documentPath)
//            return json.get(fieldName)
//        }
//        return null
//    }
//
//    @Synchronized
//    fun readFields(documentPath: String, fieldNames: ArrayList<String>): HashMap<String, Any> {
//        val result = HashMap<String, Any>()
//
//        if (doesDocumentExist(documentPath)) {
//            val json = getDocumentAsJSON(documentPath)
//
//            for (fieldName in fieldNames) {
//                if (json.has(fieldName)) {
//                    result[fieldName] = json.get(fieldName)
//                }
//            }
//        }
//
//        return result
//    }
//
//    @Synchronized
//    fun deleteField(documentPath : String, fieldName : String) : Any?{
//        if(doesDocumentExist(documentPath)){
//            val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
//            val json = getDocumentAsJSON(documentPath)
//            val fieldValue = json.remove(fieldName)
//            RCTfileX.writeFile(absolutePath, json.toString())
//            return fieldValue
//        }
//        return null
//    }
//
//    @Synchronized
//    fun deleteFields(documentPath: String, fieldNames: ArrayList<String>): HashMap<String, Any> {
//        val deletedFields = HashMap<String, Any>()
//
//        if (doesDocumentExist(documentPath)) {
//            val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
//            val json = getDocumentAsJSON(documentPath)
//
//            for (fieldName in fieldNames) {
//                if (json.has(fieldName)) {
//                    val removed = json.remove(fieldName)
//                    if (removed != null) {
//                        deletedFields[fieldName] = removed
//                    }
//                }
//            }
//
//            RCTfileX.writeFile(absolutePath, json.toString())
//        }
//
//        return deletedFields
//    }
//
//    @Synchronized
//    fun clearDocument(documentPath: String) {
//        if (doesDocumentExist(documentPath)) {
//            val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
//            RCTfileX.writeFile(absolutePath, JSONObject().toString())
//        }
//    }
//
//
//
//
//
//    fun isValidDocument(path: String): Boolean {
//        val segments = path.trim('/').split("/")
//        return segments.size > 1
//    }
//
//
//    fun doesFieldExist(jsonObject: JSONObject, fieldName: String): Boolean {
//        return jsonObject.has(fieldName)
//    }
//
//
//    private fun getDocumentAsJSON(documentPath : String) : JSONObject{
//        //Check on parent method if document exists
//        var absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
//        return JSONObject(RCTfileX.readFileAsSingleLineString(absolutePath))
//
//    }


    fun createDocument(documentPath: String){
        var parentDirectory = getParentDirectory(documentPath)
        var parentDirectoryAbsPath = getDirectoryPathAbsoluteFilePath(getParentDirectory(documentPath))

        if(!doesDirectoryExist(parentDirectory)){
            var segments = getParentDirectorySegments(documentPath)
            createDirectorySegments(segments)
        }
        var absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
        println("79308303 Created Doc Abs Path : "+absolutePath)
        if(!RCTfileX.fileExists(absolutePath)){
            RCTfileX.createFile(absolutePath)
            RCTfileX.writeFile(absolutePath,JSONObject().toString())
        }
    }

    fun putField(documentPath: String, fieldName: String, fieldValue: Any) : Boolean{
        if(!doesDocumentExist(documentPath)){
            createDocument(documentPath)
        }
        val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
        val json = getDocumentAsJSON(documentPath)
        if(json != null){
            json.put(fieldName,fieldValue)
            RCTfileX.writeFile(absolutePath,json.toString())
            return true
        }else{
            return false
        }

    }

    fun putFields(documentPath: String,fields: HashMap<String, Any>) : Boolean{
        if(!doesDocumentExist(documentPath)){
            createDocument(documentPath)
        }
        val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
        val json = getDocumentAsJSON(documentPath)
        if(json != null){
            for ((key, value) in fields) {
                json.put(key, value)
            }
            RCTfileX.writeFile(absolutePath,json.toString())
            return true
        }else{
            return false
        }
    }

    fun readField(documentPath: String, fieldName: String): Any?{
        if(doesDocumentExist(documentPath)){
            val json = getDocumentAsJSON(documentPath)
            if(json != null) {
                return json.get(fieldName)
            }
        }
        return null
    }
    fun readFields(documentPath: String, fieldNames: ArrayList<String>): HashMap<String, Any> {
        val result = HashMap<String, Any>()

        if (doesDocumentExist(documentPath)) {
            val json = getDocumentAsJSON(documentPath)
            if (json != null) {
                for (fieldName in fieldNames) {
                    if (json.has(fieldName)) {
                        result[fieldName] = json.get(fieldName)
                    }
                }
            }
        }

        return result
    }


        @Synchronized
    fun deleteField(documentPath : String, fieldName : String) : Any?{
        if(doesDocumentExist(documentPath)){
            val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
            val json = getDocumentAsJSON(documentPath)
            if(json != null) {
                val fieldValue = json.remove(fieldName)
                RCTfileX.writeFile(absolutePath, json.toString())
                return fieldValue
            }
        }
        return null
    }

    @Synchronized
    fun deleteFields(documentPath: String, fieldNames: ArrayList<String>): HashMap<String, Any> {
        val deletedFields = HashMap<String, Any>()

        if (doesDocumentExist(documentPath)) {
            val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
            val json = getDocumentAsJSON(documentPath)

            if(json != null) {
                for (fieldName in fieldNames) {
                    if (json.has(fieldName)) {
                        val removed = json.remove(fieldName)
                        if (removed != null) {
                            deletedFields[fieldName] = removed
                        }
                    }
                }
            }

            RCTfileX.writeFile(absolutePath, json.toString())
        }

        return deletedFields
    }










    fun doesDocumentExist(path: String): Boolean {
        var absolutePath = getDocumentPathAbsoluteFilePath(path)
        return RCTfileX.fileExists(absolutePath)
    }

    fun doesDirectoryExist(path : String) : Boolean{
        var absolutePath = getDirectoryPathAbsoluteFilePath(path)
        return RCTdirectoryX.directoryExists(absolutePath)
    }

    private fun getParentDirectory(documentPath: String): String {
        // Normalize the path: remove extra slashes and empty segments
        val segments = documentPath.split("/")
            .filter { it.isNotBlank() }

        return if (segments.size > 1) {
            segments.dropLast(1).joinToString("/")
        } else {
            "" // No parent directory
        }
    }


    private fun getParentDirectorySegments(documentPath: String): ArrayList<String> {
        val segments = documentPath.split("/")
            .filter { it.isNotBlank() }

        return if (segments.size > 1) {
            ArrayList(segments.dropLast(1))
        } else {
            ArrayList() // No parent directory segments
        }
    }

    private fun createDirectorySegments(segments: ArrayList<String>) {
        var currentPath = databaseDirectory.absolutePath

        for (segment in segments) {
            currentPath += "/$segment"
            RCTdirectoryX.createDirectory(currentPath)
        }
    }

    private fun getDocumentAsJSON(documentPath : String) : JSONObject?{
        if(doesDocumentExist(documentPath)){
            val absolutePath = getDocumentPathAbsoluteFilePath(documentPath)
            return JSONObject(RCTfileX.readFileAsSingleLineString(absolutePath))
        }
        return null
    }







    fun getDocumentPathAbsoluteFilePath(path : String) : String{
        return databaseDirectory.absolutePath+"/"+path+".json";
    }

    fun getDirectoryPathAbsoluteFilePath(path : String) : String{
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
