package com.racartech.library.rctandroid.google.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.racartech.library.rctandroid.google.firebase.firestore.system.FirestoreReadField
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.GeoPoint
import com.racartech.library.rctandroid.google.firebase.firestore.system.FirestoreCreateUpdateField
import com.racartech.library.rctandroid.google.firebase.firestore.system.FirestoreDeleteField
import com.racartech.library.rctandroid.google.firebase.firestore.system.FirestoreSetField
import com.racartech.library.rctandroid.google.firebase.firestore.system.`interface`.InterfaceFirestoreField

object FirestoreField : InterfaceFirestoreField {



    override suspend fun readFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): String? {
        return FirestoreReadField.readField<String>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Int? {
        return FirestoreReadField.readField<Int>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Double? {
        return FirestoreReadField.readField<Double>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Float? {
        return FirestoreReadField.readField<Double>(instance, collectionPath, documentPath, fieldName)?.toFloat()
    }

    override suspend fun readFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Long? {
        return FirestoreReadField.readField<Long>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Boolean? {
        return FirestoreReadField.readField<Boolean>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Timestamp? {
        return FirestoreReadField.readField<Timestamp>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): GeoPoint? {
        return FirestoreReadField.readField<GeoPoint>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Map<String, Any>? {
        return FirestoreReadField.readField<Map<String, Any>>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): List<Any>? {
        return FirestoreReadField.readField<List<Any>>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Blob? {
        return FirestoreReadField.readField<Blob>(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): DocumentReference? {
        return FirestoreReadField.readField<DocumentReference>(instance, collectionPath, documentPath, fieldName)
    }


    // ----------------- setField Methods -----------------

    override suspend fun setFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: String
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Int
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Double
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Float
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Long
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Boolean
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Timestamp
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: GeoPoint
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Map<String, Any>
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: List<Any>
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Blob
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: DocumentReference
    ) {
        FirestoreSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }


    // ----------------- createUpdateField Methods -----------------

    override suspend fun createUpdateFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: String
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Int
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Double
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Float
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Long
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Boolean
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Timestamp
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: GeoPoint
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Map<String, Any>
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: List<Any>
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Blob
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: DocumentReference
    ) {
        FirestoreCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }


    // ----------------- deleteField Methods -----------------

    override suspend fun deleteFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }


}
