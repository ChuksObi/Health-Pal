package com.example.chuks.healthpal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private CircleImageView circleImageView;
    private static final int PICK_IMAGE = 1;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intentImage = new Intent();
//                intentImage.setType("image/*");
//                intentImage.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intentImage, "Select Image"), PICK_IMAGE);
            }
        });


        return view;
    }


}
