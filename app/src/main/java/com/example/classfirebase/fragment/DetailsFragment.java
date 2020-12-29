package com.example.classfirebase.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class DetailsFragment extends Fragment {

    private TextView detailsName, detailsDescription;
    private GridView gridView;
    private ImageView delete,edit;
    private String uId= null;


    //firebase
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User_Note");
    private StorageReference storageReference= FirebaseStorage.getInstance().getReference();


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_details, container, false);

        detailsName= view.findViewById(R.id.detailsNameId);
        detailsDescription= view.findViewById(R.id.detailsDescriptionId);
        gridView= view.findViewById(R.id.gridViewId);
        delete= view.findViewById(R.id.deleteImageId);
        edit= view.findViewById(R.id.editImageId);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){

            DetailsFragmentArgs args= DetailsFragmentArgs.fromBundle(getArguments());
            uId= args.getNoteId();
            Toast.makeText(getActivity(), ""+uId, Toast.LENGTH_SHORT).show();

        }
    }
}