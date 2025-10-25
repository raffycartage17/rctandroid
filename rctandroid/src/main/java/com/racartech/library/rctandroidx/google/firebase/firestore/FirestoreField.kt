package com.racartech.library.rctandroidx.google.firebase.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.Query
import com.racartech.library.rctandroidx.google.firebase.firestore.`interface`.InterfaceFirestoreField
import com.racartech.library.rctandroidx.google.firebase.firestore.model.FieldData
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemCreateUpdateField
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemDeleteField
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemQuery
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemReadField
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemSetField

object FirestoreField : InterfaceFirestoreField{



    override suspend fun readFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): String? {
        return FirestoreSystemReadField.readField<String>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Int? {
        return FirestoreSystemReadField.readField<Int>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Double? {
        return FirestoreSystemReadField.readField<Double>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Float? {
        return FirestoreSystemReadField.readField<Double>(instance, collectionPath, documentPath, fieldName)?.toFloat()
    }

    override suspend fun readFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Long? {
        return FirestoreSystemReadField.readField<Long>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Boolean? {
        return FirestoreSystemReadField.readField<Boolean>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Timestamp? {
        return FirestoreSystemReadField.readField<Timestamp>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): GeoPoint? {
        return FirestoreSystemReadField.readField<GeoPoint>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Map<String, Any>? {
        return FirestoreSystemReadField.readField<Map<String, Any>>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): List<Any>? {
        return FirestoreSystemReadField.readField<List<Any>>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Blob? {
        return FirestoreSystemReadField.readField<Blob>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): DocumentReference? {
        return FirestoreSystemReadField.readField<DocumentReference>(instance, collectionPath, documentPath, fieldName)
    }



    override suspend fun readFieldAsString(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): String? {
        return FirestoreSystemReadField.readField<String>(documentData, fieldName)
    }

    override suspend fun readFieldAsInt(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Int? {
        return FirestoreSystemReadField.readField<Int>(documentData, fieldName)
    }

    override suspend fun readFieldAsDouble(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Double? {
        return FirestoreSystemReadField.readField<Double>(documentData, fieldName)
    }

    override suspend fun readFieldAsFloat(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Float? {
        return FirestoreSystemReadField.readField<Float>(documentData, fieldName)
    }

    override suspend fun readFieldAsLong(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Long? {
        return FirestoreSystemReadField.readField<Long>(documentData, fieldName)
    }

    override suspend fun readFieldAsBoolean(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Boolean? {
        return FirestoreSystemReadField.readField<Boolean>(documentData, fieldName)
    }

    override suspend fun readFieldAsTimestamp(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Timestamp? {
        return FirestoreSystemReadField.readField<Timestamp>(documentData, fieldName)
    }

    override suspend fun readFieldAsGeoPoint(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): GeoPoint? {
        return FirestoreSystemReadField.readField<GeoPoint>(documentData, fieldName)
    }

    override suspend fun readFieldAsMap(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Map<String, Any>? {
        return FirestoreSystemReadField.readField<Map<String, Any>>(documentData, fieldName)
    }

    override suspend fun readFieldAsList(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): List<Any>? {
        return FirestoreSystemReadField.readField<List<Any>>(documentData, fieldName)
    }

    override suspend fun readFieldAsBlob(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Blob? {
        return FirestoreSystemReadField.readField<Blob>(documentData, fieldName)
    }

    override suspend fun readFieldAsDocumentReference(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): DocumentReference? {
        return FirestoreSystemReadField.readField<DocumentReference>(documentData, fieldName)
    }



    // ----------------- setField Methods -----------------

    override suspend fun setFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: String
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsString(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: String
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Int
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsInt(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Int
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Double
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsDouble(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Double
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Float
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsFloat(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Float
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Long
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsLong(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Long
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Boolean
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsBoolean(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Boolean
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Timestamp
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsTimestamp(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Timestamp
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: GeoPoint
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsGeoPoint(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: GeoPoint
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Map<String, Any>
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsMap(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Map<String, Any>
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: List<Any>
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsList(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: List<Any>
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Blob
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsBlob(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: Blob
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }

    override suspend fun setFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: DocumentReference
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value)
    }

    override suspend fun setFieldAsDocumentReference(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: DocumentReference
    ) {
        FirestoreSystemSetField.setField(documentData,fieldName, value)
    }


    // ----------------- createUpdateField Methods -----------------

    override suspend fun createUpdateFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: String
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

//    override suspend fun createUpdateFieldAsString(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: String
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }


    override suspend fun createUpdateFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Int
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

//    override suspend fun createUpdateFieldAsInt(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Int
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }

    override suspend fun createUpdateFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Double
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

//    override suspend fun createUpdateFieldAsDouble(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Double
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }

    override suspend fun createUpdateFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Float
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

//    override suspend fun createUpdateFieldAsFloat(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Float
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }

    override suspend fun createUpdateFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Long
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

//    override suspend fun createUpdateFieldAsLong(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Long
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }

    override suspend fun createUpdateFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Boolean
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

//    override suspend fun createUpdateFieldAsBoolean(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Boolean
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }

    override suspend fun createUpdateFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Timestamp
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: GeoPoint
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Map<String, Any>
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: List<Any>
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Blob
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: DocumentReference
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsStringAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: String
    ): HashMap<String, Any>? {
        return FirestoreSystemCreateUpdateField.createUpdateFieldAndGetDocument(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsIntAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Int
    ): HashMap<String, Any>? {
        return FirestoreSystemCreateUpdateField.createUpdateFieldAndGetDocument(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsDoubleAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Double
    ): HashMap<String, Any>? {
        return FirestoreSystemCreateUpdateField.createUpdateFieldAndGetDocument(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsFloatAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Float
    ): HashMap<String, Any>? {
        return FirestoreSystemCreateUpdateField.createUpdateFieldAndGetDocument(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsLongAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Long
    ): HashMap<String, Any>? {
        return FirestoreSystemCreateUpdateField.createUpdateFieldAndGetDocument(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsBooleanAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Boolean
    ): HashMap<String, Any>? {
        return FirestoreSystemCreateUpdateField.createUpdateFieldAndGetDocument(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsTimestampAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Timestamp
    ): HashMap<String, Any>? {
        return FirestoreSystemCreateUpdateField.createUpdateFieldAndGetDocument(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsGeoPointAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: GeoPoint
    ): HashMap<String, Any>? {
        TODO("Not yet implemented")
    }

    override suspend fun createUpdateFieldAsMapAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Map<String, Any>
    ): HashMap<String, Any>? {
        TODO("Not yet implemented")
    }

    override suspend fun createUpdateFieldAsListAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: List<Any>
    ): HashMap<String, Any>? {
        TODO("Not yet implemented")
    }

    override suspend fun createUpdateFieldAsBlobAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Blob
    ): HashMap<String, Any>? {
        TODO("Not yet implemented")
    }

    override suspend fun createUpdateFieldAsDocumentReferenceAndGetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: DocumentReference
    ): HashMap<String, Any>? {
        TODO("Not yet implemented")
    }




//    override suspend fun createUpdateFieldTimestamp(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Timestamp
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }

//    override suspend fun createUpdateFieldGeoPoint(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: GeoPoint
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }
//
//    override suspend fun createUpdateFieldMap(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Map<String, Any>
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }
//
//    override suspend fun createUpdateFieldList(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: List<Any>
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }
//
//    override suspend fun createUpdateFieldBlob(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: Blob
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }
//
//    override suspend fun createUpdateFieldDocumentReference(
//        documentData: HashMap<String, Any>,
//        fieldName: String,
//        value: DocumentReference
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateField(documentData, fieldName, value)
//    }

//    override suspend fun createUpdateFields(
//        instance: FirebaseFirestore,
//        collectionPath: String,
//        documentPath: String,
//        fields: List<FieldData>
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateFields(instance, collectionPath, documentPath, fields)
//    }

//    override suspend fun createUpdateFields(
//        documentData: HashMap<String, Any>,
//        fields: List<FieldData>
//    ) {
//        FirestoreSystemCreateUpdateField.createUpdateFields(documentData, fields)
//    }
//
//    override suspend fun createUpdateFieldsAndGetDocument(
//        instance: FirebaseFirestore,
//        collectionPath: String,
//        documentPath: String,
//        fields: List<FieldData>
//    ): HashMap<String, Any>? {
//        return FirestoreSystemCreateUpdateField.createUpdateFieldsAndGetDocument(instance, collectionPath, documentPath, fields)
//    }

    




    // ----------------- deleteField Methods -----------------

    override suspend fun deleteField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAndGetDocumentData(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): HashMap<String, Any>? {
        return FirestoreSystemDeleteField.deleteFieldAndGetDocumentData(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): String? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? String
    }


    override suspend fun deleteFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Int? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Int
    }

    override suspend fun deleteFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Double? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Double
    }

    override suspend fun deleteFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Float? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Float
    }

    override suspend fun deleteFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Long? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Long
    }

    override suspend fun deleteFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Boolean? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Boolean
    }

    override suspend fun deleteFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Timestamp? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Timestamp
    }

    override suspend fun deleteFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): GeoPoint? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? GeoPoint
    }

    override suspend fun deleteFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Map<String, Any>? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Map<String, Any>
    }

    override suspend fun deleteFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): List<Any>? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? List<Any>
    }

    override suspend fun deleteFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Blob? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? Blob
    }

    override suspend fun deleteFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): DocumentReference? {
        val result = FirestoreSystemDeleteField.deleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
        return result as? DocumentReference
    }


    //////////////////////////////////////////////////////////////


    override suspend fun query(
        query: Query
    ): HashMap<String, Map<String, Any?>> {
        return FirestoreSystemQuery.executeQuery(query)
    }




}
