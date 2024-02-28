package com.manuni.usermadrasha.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manuni.usermadrasha.R;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;


public class GalleryFragment extends Fragment {

RecyclerView mdrasarTotthoRecycler, sikkhokhRecycler, procharRecycler, onnannoRecycler;
GalleryAdapter adapter;

DatabaseReference reference;
private List<ImageModel> list1, list2, list3, list4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);

        mdrasarTotthoRecycler = view.findViewById(R.id.mdrasarTotthoRecycler);
        sikkhokhRecycler = view.findViewById(R.id.sikkhokhRecycler);
        procharRecycler = view.findViewById(R.id.procharRecycler);
        onnannoRecycler = view.findViewById(R.id.onnannoRecycler);


        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getMadrasaTottho();
        getsikkhokh();
        getprochar();
        getonnanno();

        return view;
    }

    private void getMadrasaTottho() {
        reference.child("মাদ্রাসার তথ্যসমূহ").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                list1.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                   ImageModel model = dataSnapshot.getValue(ImageModel.class);
                    list1.add(0,model);
                }
                adapter = new GalleryAdapter(getContext(),list1);
                mdrasarTotthoRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                mdrasarTotthoRecycler.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getsikkhokh(){
        reference.child("শিক্ষক এবং ছাত্রতথ্য").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                list2.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ImageModel model = dataSnapshot.getValue(ImageModel.class);
                    list2.add(0,model);
                }
                adapter = new GalleryAdapter(getContext(),list2);
                sikkhokhRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                sikkhokhRecycler.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getprochar(){
        reference.child("প্রচার ও প্রকাশনাসমূহ").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                list3.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ImageModel model =  dataSnapshot.getValue(ImageModel.class);
                    list3.add(0,model);
                }
                adapter = new GalleryAdapter(getContext(),list3);
                procharRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                procharRecycler.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getonnanno(){
        reference.child("অন্যান্য").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                list4.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ImageModel model = dataSnapshot.getValue(ImageModel.class);
                    list4.add(0,model);
                }
                adapter = new GalleryAdapter(getContext(),list4);
                onnannoRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                onnannoRecycler.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}