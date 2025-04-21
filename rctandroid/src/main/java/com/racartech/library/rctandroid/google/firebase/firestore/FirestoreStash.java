package com.racartech.library.rctandroid.google.firebase.firestore;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class FirestoreStash{

    private FirebaseFirestore fs_instance;
    private String collection;
    private String document;
    private HashMap<String,Object> data = new HashMap<String, Object>();

    private ArrayList<FirestoreStashCommand> changes = new ArrayList<>();

    public FirestoreStash(FirebaseFirestore fs_instance, String document){
        this.fs_instance = fs_instance;
        this.collection = null;
        this.document = document;
        this.data = loadDocumentDataFromServer(this.fs_instance,this.collection, this.document);
    }

    public FirestoreStash(String document, HashMap<String, Object> data){
        this.collection = null;
        this.document = document;
        this.data = data;
    }

    public FirestoreStash(String collection, String document){
        this.collection = collection;
        this.document = document;
    }

    public FirestoreStash(String collection, String document, HashMap<String, Object> data){
        this.collection = collection;
        this.document = document;
        this.data = data;
    }


    private HashMap<String, Object> loadDocumentDataFromServer(FirebaseFirestore fs_instance, String collection, String document){
        return RCTfirebaseFirestore.readDocument(fs_instance,this.collection,this.document,100L);
    }

    public void createField(String key, String value){
        this.changes.add(new FirestoreStashCommand(FirestoreStashCommand.CommandType.CREATE,key,value));
    }
    public void deleteField(String key){
        this.changes.add(new FirestoreStashCommand(FirestoreStashCommand.CommandType.DELETE,key));
    }
    public void updateField(String key, String value){
        this.changes.add(new FirestoreStashCommand(FirestoreStashCommand.CommandType.UPDATE,key,value));
    }

    public void syncDataFromServer(){
        this.data = loadDocumentDataFromServer(this.fs_instance,this.collection, this.document);
    }

    public void syncChanges(boolean sync_with_server_first){

        if(sync_with_server_first){
            syncDataFromServer();
        }

        for(int index = 0; index< this.changes.size(); index++){
            FirestoreStashCommand current_command = this.changes.get(index);
            switch (current_command.commandType){
                case CREATE:
                    handleCreateCommand(current_command);
                    break;
                case DELETE:
                    handleDeleteCommand(current_command);
                    break;
                case UPDATE:
                    handleUpdateCommand(current_command);
                    break;
            }
        }

        uploadModifiedDataToServer();
        clearChanges();


    }



    public void clearChanges() {
        this.changes.clear();
    }


    private void uploadModifiedDataToServer(){
        RCTfirebaseFirestore.setDocument_WaitProgress(this.fs_instance,this.collection, this.document, this.data,100);
    }

    private void handleCreateCommand(FirestoreStashCommand command){
        putOrUpdateField(command.getKey(),command.getValue());
    }
    private void handleUpdateCommand(FirestoreStashCommand command){
        putOrUpdateField(command.getKey(),command.getValue());
    }
    private void handleDeleteCommand(FirestoreStashCommand command){
        this.data.remove(command.getKey());
    }


    private void putOrUpdateField(String key, Object value){
        this.data.put(key,value);
    }


























    private static class FirestoreStashCommand {

        public enum CommandType {
            CREATE,
            UPDATE,
            DELETE
        }

        private String key = null;
        private Object value = null;
        private CommandType commandType = null;


        public FirestoreStashCommand(CommandType commandType, String key) {
            this.commandType = commandType;
            this.key = key;
        }
        public FirestoreStashCommand(CommandType commandType, String key, Object value) {
            this.commandType = commandType;
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public CommandType getCommandType() {
            return commandType;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }










}
