package com.racartech.library.rctandroidx.google.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.GeoPoint
import com.racartech.library.rctandroidx.google.firebase.firestore.`interface`.InterfaceFirestoreDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemCreateUpdateField
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemDeleteField
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemReadField
import com.racartech.library.rctandroidx.google.firebase.firestore.system.field.FirestoreSystemSetField
import com.racartech.library.rctandroidx.google.firebase.firestore.`interface`.InterfaceFirestoreField
import com.racartech.library.rctandroidx.google.firebase.firestore.model.FieldData
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirebaseSystemCreateSetDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirebaseSystemDeleteDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirestoreSystemCreateMergeDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirestoreSystemReadDocument

object FirestoreField : InterfaceFirestoreField, InterfaceFirestoreDocument {



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


    // ----------------- setField Methods -----------------

    override suspend fun setFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: String
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Int
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Double
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Float
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Long
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Boolean
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Timestamp
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: GeoPoint
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Map<String, Any>
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: List<Any>
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Blob
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
    }

    override suspend fun setFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: DocumentReference
    ) {
        FirestoreSystemSetField.setField(instance,collectionPath,documentPath,fieldName, value);
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


    override suspend fun createUpdateFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Int
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Double
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Float
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Long
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

    override suspend fun createUpdateFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Boolean
    ) {
        FirestoreSystemCreateUpdateField.createUpdateField(instance, collectionPath, documentPath, fieldName, value)
    }

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

    override suspend fun createUpdateFields(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fields: List<FieldData>
    ) {
        FirestoreSystemCreateUpdateField.createUpdateFields(instance, collectionPath, documentPath, fields)
    }


    // ----------------- deleteField Methods -----------------

    override suspend fun deleteFieldAsString(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsInt(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsDouble(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsFloat(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsLong(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsBoolean(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsTimestamp(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsGeoPoint(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsMap(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsList(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsBlob(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun deleteFieldAsDocumentReference(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        FirestoreSystemDeleteField.deleteField(instance, collectionPath, documentPath, fieldName)
    }

    override suspend fun readDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String): HashMap<String, Any>? {
        return FirestoreSystemReadDocument.readDocument(instance, collectionPath, documentPath);
    }

    override suspend fun createMergeDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        contents: HashMap<String, Any>
    ): Boolean {
        return FirestoreSystemCreateMergeDocument.createMergeDocument(instance, collectionPath, documentPath, contents)
    }

    override suspend fun createSetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        contents: HashMap<String, Any>
    ): Boolean {
        return FirebaseSystemCreateSetDocument.createOrSetDocument(instance,  collectionPath, documentPath, contents);
    }

    override suspend fun deleteDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): Boolean {
        return FirebaseSystemDeleteDocument.deleteDocument(instance, collectionPath, documentPath)
    }


}
