package com.example.chuks.healthpal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetUpActivity extends AppCompatActivity {

    private CircleImageView mCirleImage;
    private Button mButton;
    private static final int PICK_IMAGE = 1;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        mCirleImage = (CircleImageView) findViewById(R.id.addImage);
        mButton = (Button) findViewById(R.id.nextButton);

        imageUri = null;


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
