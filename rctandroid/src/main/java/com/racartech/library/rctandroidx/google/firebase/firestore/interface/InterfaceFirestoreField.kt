package com.racartech.library.rctandroidx.google.firebase.firestore.`interface`

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.racartech.library.rctandroidx.google.firebase.firestore.model.FieldData

internal interface InterfaceFirestoreField {


    // ----------------- readField Method -----------------

    suspend fun readFieldAsString(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): String?
    suspend fun readFieldAsInt(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Int?
    suspend fun readFieldAsDouble(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Double?
    suspend fun readFieldAsFloat(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Float?
    suspend fun readFieldAsLong(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Long?
    suspend fun readFieldAsBoolean(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Boolean?
    suspend fun readFieldAsTimestamp(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Timestamp?
    suspend fun readFieldAsGeoPoint(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): GeoPoint?
    suspend fun readFieldAsMap(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Map<String, Any>?
    suspend fun readFieldAsList(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): List<Any>?
    suspend fun readFieldAsBlob(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): Blob?
    suspend fun readFieldAsDocumentReference(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): DocumentReference?

    suspend fun readFieldAsString(documentData : HashMap<String, Any>,fieldName: String): String?
    suspend fun readFieldAsInt(documentData : HashMap<String, Any>, fieldName: String): Int?
    suspend fun readFieldAsDouble(documentData : HashMap<String, Any>, fieldName: String): Double?
    suspend fun readFieldAsFloat(documentData : HashMap<String, Any>, fieldName: String): Float?
    suspend fun readFieldAsLong(documentData : HashMap<String, Any>, fieldName: String): Long?
    suspend fun readFieldAsBoolean(documentData : HashMap<String, Any>, fieldName: String): Boolean?
    suspend fun readFieldAsTimestamp(documentData : HashMap<String, Any>, fieldName: String): Timestamp?
    suspend fun readFieldAsGeoPoint(documentData : HashMap<String, Any>, fieldName: String): GeoPoint?
    suspend fun readFieldAsMap(documentData : HashMap<String, Any>, fieldName: String): Map<String, Any>?
    suspend fun readFieldAsList(documentData : HashMap<String, Any>, fieldName: String): List<Any>?
    suspend fun readFieldAsBlob(documentData : HashMap<String, Any>, fieldName: String): Blob?
    suspend fun readFieldAsDocumentReference(documentData : HashMap<String, Any>, fieldName: String): DocumentReference?

    // ----------------- setField Methods -----------------

    suspend fun setFieldAsString(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: String)
    suspend fun setFieldAsInt(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Int)
    suspend fun setFieldAsDouble(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Double)
    suspend fun setFieldAsFloat(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Float)
    suspend fun setFieldAsLong(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Long)
    suspend fun setFieldAsBoolean(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Boolean)
    suspend fun setFieldAsTimestamp(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Timestamp)
    suspend fun setFieldAsGeoPoint(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: GeoPoint)
    suspend fun setFieldAsMap(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Map<String, Any>)
    suspend fun setFieldAsList(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: List<Any>)
    suspend fun setFieldAsBlob(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Blob)
    suspend fun setFieldAsDocumentReference(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: DocumentReference)

    suspend fun setFieldAsString(documentData: HashMap<String, Any>, fieldName: String, value: String)
    suspend fun setFieldAsInt(documentData: HashMap<String, Any>, fieldName: String, value: Int)
    suspend fun setFieldAsDouble(documentData: HashMap<String, Any>, fieldName: String, value: Double)
    suspend fun setFieldAsFloat(documentData: HashMap<String, Any>, fieldName: String, value: Float)
    suspend fun setFieldAsLong(documentData: HashMap<String, Any>, fieldName: String, value: Long)
    suspend fun setFieldAsBoolean(documentData: HashMap<String, Any>, fieldName: String, value: Boolean)
    suspend fun setFieldAsTimestamp(documentData: HashMap<String, Any>, fieldName: String, value: Timestamp)
    suspend fun setFieldAsGeoPoint(documentData: HashMap<String, Any>, fieldName: String, value: GeoPoint)
    suspend fun setFieldAsMap(documentData: HashMap<String, Any>, fieldName: String, value: Map<String, Any>)
    suspend fun setFieldAsList(documentData: HashMap<String, Any>, fieldName: String, value: List<Any>)
    suspend fun setFieldAsBlob(documentData: HashMap<String, Any>, fieldName: String, value: Blob)
    suspend fun setFieldAsDocumentReference(documentData: HashMap<String, Any>, fieldName: String, value: DocumentReference)

    // ----------------- updateField Method -----------------

    suspend fun createUpdateFieldAsString(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: String)
    suspend fun createUpdateFieldAsInt(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Int)
    suspend fun createUpdateFieldAsDouble(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Double)
    suspend fun createUpdateFieldAsFloat(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Float)
    suspend fun createUpdateFieldAsLong(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Long)
    suspend fun createUpdateFieldAsBoolean(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Boolean)
    suspend fun createUpdateFieldAsTimestamp(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Timestamp)
    suspend fun createUpdateFieldAsGeoPoint(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: GeoPoint)
    suspend fun createUpdateFieldAsMap(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Map<String, Any>)
    suspend fun createUpdateFieldAsList(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: List<Any>)
    suspend fun createUpdateFieldAsBlob(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Blob)
    suspend fun createUpdateFieldAsDocumentReference(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: DocumentReference)

    suspend fun createUpdateFieldAsStringAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: String): HashMap<String, Any>?
    suspend fun createUpdateFieldAsIntAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Int): HashMap<String, Any>?
    suspend fun createUpdateFieldAsDoubleAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Double): HashMap<String, Any>?
    suspend fun createUpdateFieldAsFloatAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Float): HashMap<String, Any>?
    suspend fun createUpdateFieldAsLongAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Long): HashMap<String, Any>?
    suspend fun createUpdateFieldAsBooleanAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Boolean): HashMap<String, Any>?
    suspend fun createUpdateFieldAsTimestampAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Timestamp): HashMap<String, Any>?
    suspend fun createUpdateFieldAsGeoPointAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: GeoPoint): HashMap<String, Any>?
    suspend fun createUpdateFieldAsMapAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Map<String, Any>): HashMap<String, Any>?
    suspend fun createUpdateFieldAsListAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: List<Any>): HashMap<String, Any>?
    suspend fun createUpdateFieldAsBlobAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: Blob): HashMap<String, Any>?
    suspend fun createUpdateFieldAsDocumentReferenceAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String, value: DocumentReference): HashMap<String, Any>?


    suspend fun createUpdateFieldAsString(documentData: HashMap<String, Any>, fieldName: String, value: String)
    suspend fun createUpdateFieldAsInt(documentData: HashMap<String, Any>, fieldName: String, value: Int)
    suspend fun createUpdateFieldAsDouble(documentData: HashMap<String, Any>, fieldName: String, value: Double)
    suspend fun createUpdateFieldAsFloat(documentData: HashMap<String, Any>, fieldName: String, value: Float)
    suspend fun createUpdateFieldAsLong(documentData: HashMap<String, Any>, fieldName: String, value: Long)
    suspend fun createUpdateFieldAsBoolean(documentData: HashMap<String, Any>, fieldName: String, value: Boolean)
    suspend fun createUpdateFieldTimestamp(documentData: HashMap<String, Any>, fieldName: String, value: Timestamp)
    suspend fun createUpdateFieldGeoPoint(documentData: HashMap<String, Any>, fieldName: String, value: GeoPoint)
    suspend fun createUpdateFieldMap(documentData: HashMap<String, Any>, fieldName: String, value: Map<String, Any>)
    suspend fun createUpdateFieldList(documentData: HashMap<String, Any>, fieldName: String, value: List<Any>)
    suspend fun createUpdateFieldBlob(documentData: HashMap<String, Any>, fieldName: String, value: Blob)
    suspend fun createUpdateFieldDocumentReference(documentData: HashMap<String, Any>, fieldName: String, value: DocumentReference)

    suspend fun createUpdateFields(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fields : List<FieldData>)
    suspend fun createUpdateFieldsAndGetDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fields : List<FieldData>): HashMap<String, Any>?
    suspend fun createUpdateFields(documentData: HashMap<String, Any>, fields : List<FieldData>)

    // ----------------- deleteField Method -----------------

    suspend fun deleteFieldAsString(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsInt(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsDouble(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsFloat(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsLong(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsBoolean(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsTimestamp(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsGeoPoint(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsMap(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsList(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsBlob(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)
    suspend fun deleteFieldAsDocumentReference(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String)

    suspend fun deleteAndGetFieldAsString(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsInt(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsDouble(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsFloat(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsLong(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsBoolean(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsTimestamp(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsGeoPoint(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsMap(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsList(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsBlob(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?
    suspend fun deleteAndGetFieldAsDocumentReference(instance: FirebaseFirestore, collectionPath: String, documentPath: String, fieldName: String): HashMap<String, Any>?

    suspend fun deleteFieldAsString(documentData: HashMap<String, Any>, fieldName: String) : String?
    suspend fun deleteFieldAsInt(documentData: HashMap<String, Any>, fieldName: String) : Int?
    suspend fun deleteFieldAsDouble(documentData: HashMap<String, Any>, fieldName: String) : Double?
    suspend fun deleteFieldAsFloat(documentData: HashMap<String, Any>, fieldName: String) : Float?
    suspend fun deleteFieldAsLong(documentData: HashMap<String, Any>, fieldName: String) : Long?
    suspend fun deleteFieldAsBoolean(documentData: HashMap<String, Any>, fieldName: String) : Boolean?
    suspend fun deleteFieldAsTimestamp(documentData: HashMap<String, Any>, fieldName: String) : Timestamp?
    suspend fun deleteFieldAsGeoPoint(documentData: HashMap<String, Any>, fieldName: String) : GeoPoint?
    suspend fun deleteFieldAsMap(documentData: HashMap<String, Any>, fieldName: String) : Map<String, Any>?
    suspend fun deleteFieldAsList(documentData: HashMap<String, Any>, fieldName: String) : List<Any>?
    suspend fun deleteFieldAsBlob(documentData: HashMap<String, Any>, fieldName: String) : Blob
    suspend fun deleteFieldAsDocumentReference(documentData: HashMap<String, Any>, fieldName: String) : DocumentReference?

    suspend fun deleteField(documentData : HashMap<String, Any>, fieldName : String)

}
