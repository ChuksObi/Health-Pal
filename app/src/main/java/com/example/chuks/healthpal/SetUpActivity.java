package com.example.chuks.healthpal;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetUpActivity extends AppCompatActivity {

    private CircleImageView mCirleImage;
    private Button mButton;
    private static final int PICK_IMAGE = 1;
    private Uri imageUri;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    private FirebaseFirestore mFireStore;
    private String name = "YOUR NAME";
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        mCirleImage = (CircleImageView) findViewById(R.id.addImage);
        mButton = (Button) findViewById(R.id.nextButton);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("profileImage");
        mFireStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        imageUri = null;

        mProgressBar.setVisibility(View.INVISIBLE);
        //TODO: Pass the name in an intent and append it to screen, change the varable name

        mCirleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentImage = new Intent();
                intentImage.setType("image/*");
                intentImage.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intentImage, "Select Image"), PICK_IMAGE);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userID = mAuth.getCurrentUser().getUid();

                StorageReference user_profile = mStorageRef.child(userID + ".jpg");
                user_profile.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful()){
                            mProgressBar.setVisibility(View.VISIBLE);

                            String download_uri = task.getResult().getDownloadUrl().toString();

                            HashMap <String, Object> usersMap= new HashMap<>();

                            usersMap.put("name",name );
                            usersMap.put("image", download_uri);

                            mFireStore.collection("Users").document(userID).set(usersMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent mainActivity = new Intent(SetUpActivity.this, MainActivity.class);
                                    startActivity(mainActivity);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    mProgressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Uh-0h, you have an error, please try again later", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Uh-0h, you have an error, please try again later", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                mProgressBar.setVisibility(View.INVISIBLE);
                Intent sendToMainActivity = new Intent(SetUpActivity.this, MainActivity.class);
            }
        });
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);

        if (resultCode == PICK_IMAGE){
            imageUri = data.getData();
            mCirleImage.setImageURI(imageUri);
        }
    }
}
