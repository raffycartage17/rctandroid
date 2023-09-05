package com.racartech.library.rctandroid.snippets.googlesignin;

import android.content.Context;
import android.widget.Toast;

public class SnippetGSI_FirstActivity {

    public static void test(Context context){
        Toast.makeText(context, "Snippet - Google Sign In First Activity", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
    }


/*

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.racartech.library.rctandroid.view.RCTview;

public class LoginActivity extends AppCompatActivity {


    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    private ImageButton sign_in_imagebutton;
    private Button sign_in_button;
    private Button debug_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        sign_in_button = findViewById(R.id.login_signin_button);
        sign_in_imagebutton = findViewById(R.id.login_google_icon_imagebutton);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(LoginActivity.this,gso);

        RCTview.addButtonPressedFilters(sign_in_imagebutton,getColor(R.color.white));






        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        sign_in_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



    }

    private void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
        }

    }
    private void navigateToSecondActivity(){

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);


        finish();
        Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
        startActivity(intent);
    }


}


*/


}
