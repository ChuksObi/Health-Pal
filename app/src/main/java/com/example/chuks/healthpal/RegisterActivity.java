package com.example.chuks.healthpal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "CREATE";
    private static final Integer SIX = 6;
    private EditText mUserName;
    private EditText mUserEmail;
    private EditText mUserPassword;
    private Button mCreateAccount;

    private FirebaseAuth mAuth;

    private ProgressBar mProgressBar;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserName = (EditText) findViewById(R.id.registerNameEditText);
        mUserEmail = (EditText) findViewById(R.id.registerEmailEditText);
        mUserPassword = (EditText) findViewById(R.id.loginPasswordEditText);
        mCreateAccount = (Button) findViewById(R.id.createAccountButton);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);






        mAuth = FirebaseAuth.getInstance();

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mUserNameString = mUserName.getEditableText().toString();
                String mUserEmailString = mUserEmail.getEditableText().toString();
                String mUserPasswordString = mUserPassword.getEditableText().toString();

               if (TextUtils.isEmpty(mUserNameString)){
                    Toast.makeText(RegisterActivity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mUserEmailString)){
                    Toast.makeText(RegisterActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mUserPasswordString)){
                    Toast.makeText(RegisterActivity.this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
                } else if (mUserPasswordString.length() >= SIX ){
                   Toast.makeText(RegisterActivity.this, "Your Password must be more than 6 Characters", Toast.LENGTH_SHORT).show();
               } else if(!TextUtils.isEmpty(mUserEmailString) && !TextUtils.isEmpty(mUserNameString) && !TextUtils.isEmpty(mUserPasswordString)){
                    mProgressBar.setVisibility(View.VISIBLE);
                    startRegisterOfUsers(mUserNameString,mUserEmailString,mUserPasswordString);
                }



                finish();
            }
        });





    }

    private void startRegisterOfUsers(String mUserNameString, String mUserEmailString, String mUserPasswordString) {

        mAuth.createUserWithEmailAndPassword(mUserEmailString, mUserPasswordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "User Creation: success");
                            mProgressBar.setVisibility(View.INVISIBLE);

                            Toast.makeText(RegisterActivity.this,"We are in", Toast.LENGTH_SHORT).show();

                            Intent sendToAccountSetup = new Intent(RegisterActivity.this, SetUpActivity.class);
                            startActivity(sendToAccountSetup);


                            Intent loggingInNewUserIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(loggingInNewUserIntent);
                        } else {

                            mProgressBar.setVisibility(View.INVISIBLE);
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Error: Authentication failed. Try again",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
