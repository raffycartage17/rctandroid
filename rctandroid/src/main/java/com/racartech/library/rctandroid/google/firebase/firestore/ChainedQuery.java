package com.racartech.library.rctandroid.google.firebase.firestore;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.List;

public class ChainedQuery{

    private Query CHAINED_QUERY;

    public ChainedQuery(FirebaseFirestore instance, String collection){
        CHAINED_QUERY = instance.collection(collection);
    }


    protected Query getChainedQuery(){
        return this.CHAINED_QUERY;
    }

    public void addOrderBy(String field){
        this.CHAINED_QUERY = this.CHAINED_QUERY.orderBy(field);
    }

    public void addOrderBy(FieldPath fieldPath){
        this.CHAINED_QUERY = this.CHAINED_QUERY.orderBy(fieldPath);
    }

    public void addOrderBy(String field, Query.Direction direction){
        this.CHAINED_QUERY = this.CHAINED_QUERY.orderBy(field,direction);
    }

    public void addOrderBy(FieldPath fieldPath, Query.Direction direction){
        this.CHAINED_QUERY = this.CHAINED_QUERY.orderBy(fieldPath, direction);
    }


    public void addLimit(long limit){
        this.CHAINED_QUERY = this.CHAINED_QUERY.limit(limit);
    }

    public void addLimitToLast(long limit){
        this.CHAINED_QUERY = this.CHAINED_QUERY.limitToLast(limit);
    }



    public void addStartAt(DocumentSnapshot document_snapshot){
        this.CHAINED_QUERY = this.CHAINED_QUERY.startAt(document_snapshot);
    }

    public void addStartAt(Object... fieldValues){
        this.CHAINED_QUERY = this.CHAINED_QUERY.startAt(fieldValues);
    }

    public void addStartAfter(DocumentSnapshot document_snapshot){
        this.CHAINED_QUERY = this.CHAINED_QUERY.startAfter(document_snapshot);
    }

    public void addStartAfter(Object... fieldValues){
        this.CHAINED_QUERY = this.CHAINED_QUERY.startAfter(fieldValues);
    }

    public void addEndAt(DocumentSnapshot document_snapshot){
        this.CHAINED_QUERY = this.CHAINED_QUERY.endAt(document_snapshot);
    }

    public void addEndAt(Object... fieldValues){
        this.CHAINED_QUERY = this.CHAINED_QUERY.endAt(fieldValues);
    }

    public void addEndBefore(DocumentSnapshot document_snapshot){
        this.CHAINED_QUERY = this.CHAINED_QUERY.endBefore(document_snapshot);
    }

    public void addEndBefore(Object... fieldValues){
        this.CHAINED_QUERY = this.CHAINED_QUERY.endBefore(fieldValues);
    }





    public void addWhereArrayContainsAny(@NonNull String field, @NonNull List<? extends Object> values){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereArrayContains(field,values);
    }

    public void addWhereArrayContainsAny(@NonNull FieldPath fieldPath, @NonNull List<? extends Object> values){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereArrayContains(fieldPath,values);
    }

    public void addWhereArrayContains(String field, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereArrayContains(field,value);
    }

    public void addWhereArrayContains(FieldPath field_path, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereArrayContains(field_path,value);
    }


    public void addWhereIn(@NonNull String field, @NonNull List<? extends Object> values){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereIn(field, values);
    }

    public void addWhereIn(@NonNull FieldPath fieldPath, @NonNull List<? extends Object> values){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereIn(fieldPath, values);
    }

    public void addWhereNotIn(@NonNull String field, @NonNull List<? extends Object> values){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereNotIn(field, values);
    }

    public void addWhereNotIn(@NonNull FieldPath fieldPath, @NonNull List<? extends Object> values){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereNotIn(fieldPath, values);
    }



    public void addFilter(Filter filter){
        this.CHAINED_QUERY = this.CHAINED_QUERY.where(filter);
    }


    public void addWhereLessThan(String key, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereLessThan(key, value);
    }

    public void addWhereLessThan(FieldPath field_path, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereLessThan(field_path, value);
    }

    public void addWhereLessThanOrEqualTo(String key, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereLessThanOrEqualTo(key, value);
    }

    public void addWhereLessThanOrEqualTo(FieldPath field_path, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereLessThanOrEqualTo(field_path, value);
    }


    public void addWhereGreaterThan(String key, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereGreaterThan(key, value);
    }

    public void addWhereGreaterThan(FieldPath field_path, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereGreaterThan(field_path, value);
    }

    public void addWhereGreaterThanOrEqualTo(String key, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereGreaterThanOrEqualTo(key, value);
    }

    public void addWhereGreaterThanOrEqualTo(FieldPath field_path, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereGreaterThanOrEqualTo(field_path, value);
    }

    public void addWhereNotEqualTo(String key, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereNotEqualTo(key, value);
    }

    public void addWhereNotEqualTo(FieldPath field_path, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereNotEqualTo(field_path, value);
    }

    public void addWhereEqualTo(String key, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereEqualTo(key, value);
    }

    public void addWhereEqualTo(FieldPath field_path, Object value){
        this.CHAINED_QUERY = this.CHAINED_QUERY.whereEqualTo(field_path, value);
    }







}
