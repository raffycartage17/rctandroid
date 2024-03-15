package com.racartech.library.rctandroid.snippets.firebase;

import android.content.Context;
import android.widget.Toast;

public class SnippetFirestore {





    public static void test(Context context){
        Toast.makeText(context, "Snippet Firestore", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
    }


    /*
    public void addFirestoreDocumentListener(FirebaseFirestore instance){

        //AccountDataUtil.getFirestoreCollectionPathForUserData(account_data),
        //                    AccountData.CURRENT_USER_SUGGESTIONS_DOCUMENT_ID
        String collectionPath = AccountDataUtil.getFirestoreCollectionPathForUserData(
                ApplicationData.CURRENT_USER_ACCOUNT_DATA.get());
        String documentName = AccountData.CURRENT_USER_SUGGESTIONS_DOCUMENT_ID;

        String documentPath = collectionPath.concat("/").concat(documentName);

        instance.document(documentPath).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (snapshot.getData() != null) {
                                    // You can access specific fields using snapshot.get("fieldName")
                                    HashMap<String, Object> document_data = (HashMap<String, Object>) snapshot.getData();



                                }
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
    }
     */

}
