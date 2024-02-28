package com.manuni.usermadrasha.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manuni.usermadrasha.R;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {
    private RecyclerView QuranDepartment, ArabicDepartment, urduDepartment, talimulDepartment, banglaDepartment, englishDepartment, mathematicsDepartment, computerDepartment, hefzoDepartment, keratDepartment;
    private LinearLayout QuranNoData, ArabicNoData, urduNoData, talimulNoData, banglaNoData, englishNoData, mathematicsNoData, computerNoData, hefzoNoData, keratNoData;
    private List<TeacherData> list1, list2, list3, list4, list5, list6, list7, list8, list9, list10;
    private DatabaseReference reference, dbRef;
    private TeacherAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty, container, false);
        QuranDepartment = view.findViewById(R.id.QuranDepartment);
        ArabicDepartment = view.findViewById(R.id.ArabicDepartment);
        urduDepartment = view.findViewById(R.id.urduDepartment);
        talimulDepartment = view.findViewById(R.id.talimulDepartment);
        banglaDepartment = view.findViewById(R.id.banglaDepartment);
        englishDepartment = view.findViewById(R.id.englishDepartment);
        mathematicsDepartment = view.findViewById(R.id.mathematicsDepartment);
        computerDepartment = view.findViewById(R.id.computerDepartment);
        hefzoDepartment = view.findViewById(R.id.hefzoDepartment);
        keratDepartment = view.findViewById(R.id.keratDepartment);

        QuranNoData = view.findViewById(R.id.QuranNoData);
        ArabicNoData = view.findViewById(R.id.ArabicNoData);
        urduNoData = view.findViewById(R.id.urduNoData);
        talimulNoData = view.findViewById(R.id.talimulNoData);
        banglaNoData = view.findViewById(R.id.banglaNoData);
        englishNoData = view.findViewById(R.id.englishNoData);
        mathematicsNoData = view.findViewById(R.id.mathematicsNoData);
        computerNoData = view.findViewById(R.id.computerNoData);
        hefzoNoData = view.findViewById(R.id.hefzoNoData);
        keratNoData = view.findViewById(R.id.keratNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        quranDepartment();
        arabicDepartment();
        UrduDepartment();
        TalimulDepartment();
        BanglaDepartment();
        EnglishDepartment();
        MathematicsDepartment();
        ComputerDepartment();
        HefzoDepartment();
        KeratDepartment();

        return view;


    }

    private void ComputerDepartment() {
        dbRef = reference.child("কম্পিউটার প্রশিক্ষণ");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list8 = new ArrayList<>();
                if (!snapshot.exists()){
                    computerNoData.setVisibility(View.VISIBLE);
                    computerDepartment.setVisibility(View.GONE);
                }else {
                    computerNoData.setVisibility(View.GONE);
                    computerDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    computerDepartment.setHasFixedSize(true);
                    computerDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list8,getContext());
                    computerDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void HefzoDepartment() {
        dbRef = reference.child("হেফজ বিভাগ");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list9 = new ArrayList<>();
                if (!snapshot.exists()){
                    hefzoNoData.setVisibility(View.VISIBLE);
                    hefzoDepartment.setVisibility(View.GONE);
                }else {
                    hefzoNoData.setVisibility(View.GONE);
                    hefzoDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list9.add(data);
                    }
                    hefzoDepartment.setHasFixedSize(true);
                    hefzoDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list9,getContext());
                    hefzoDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void KeratDepartment() {
        dbRef = reference.child("কেরাত বিভাগ");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list10 = new ArrayList<>();
                if (!snapshot.exists()){
                    keratNoData.setVisibility(View.VISIBLE);
                    keratDepartment.setVisibility(View.GONE);
                }else {
                    keratNoData.setVisibility(View.GONE);
                    keratDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list10.add(data);
                    }
                    keratDepartment.setHasFixedSize(true);
                    keratDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list10,getContext());
                    keratDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void quranDepartment() {
        dbRef = reference.child("কুরআন ও তাজবিদ");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                if (!snapshot.exists()){
                    QuranNoData.setVisibility(View.VISIBLE);
                    QuranDepartment.setVisibility(View.GONE);
                }else {
                    QuranNoData.setVisibility(View.GONE);
                    QuranDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    QuranDepartment.setHasFixedSize(true);
                    QuranDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1,getContext());
                    QuranDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    } private void arabicDepartment() {
        dbRef = reference.child("আরবি সাহিত্য");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()){
                    ArabicNoData.setVisibility(View.VISIBLE);
                    ArabicDepartment.setVisibility(View.GONE);
                }else {
                    ArabicNoData.setVisibility(View.GONE);
                    ArabicDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    ArabicDepartment.setHasFixedSize(true);
                    ArabicDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2,getContext());
                    ArabicDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    } private void UrduDepartment() {
        dbRef = reference.child("উর্দু কিতাব");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if (!snapshot.exists()){
                    urduNoData.setVisibility(View.VISIBLE);
                    urduDepartment.setVisibility(View.GONE);
                }else {
                    urduNoData.setVisibility(View.GONE);
                    urduDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    urduDepartment.setHasFixedSize(true);
                    urduDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3,getContext());
                    urduDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    } private void TalimulDepartment() {
        dbRef = reference.child("ইসলামিয়াত");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if (!snapshot.exists()){
                    talimulNoData.setVisibility(View.VISIBLE);
                    talimulDepartment.setVisibility(View.GONE);
                }else {
                    talimulNoData.setVisibility(View.GONE);
                    talimulDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    talimulDepartment.setHasFixedSize(true);
                    talimulDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4,getContext());
                    talimulDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    } private void BanglaDepartment() {
        dbRef = reference.child("বাংলা");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5 = new ArrayList<>();
                if (!snapshot.exists()){
                    banglaNoData.setVisibility(View.VISIBLE);
                    banglaDepartment.setVisibility(View.GONE);
                }else {
                    banglaNoData.setVisibility(View.GONE);
                    banglaDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    banglaDepartment.setHasFixedSize(true);
                    banglaDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list5,getContext());
                    banglaDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    } private void EnglishDepartment() {
        dbRef = reference.child("ইংরেজি");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list6 = new ArrayList<>();
                if (!snapshot.exists()){
                    englishNoData.setVisibility(View.VISIBLE);
                    englishDepartment.setVisibility(View.GONE);
                }else {
                    englishNoData.setVisibility(View.GONE);
                    englishDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    englishDepartment.setHasFixedSize(true);
                    englishDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list6,getContext());
                    englishDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    } private void MathematicsDepartment() {
        dbRef = reference.child("গণিত");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list7 = new ArrayList<>();
                if (!snapshot.exists()){
                    mathematicsNoData.setVisibility(View.VISIBLE);
                    mathematicsDepartment.setVisibility(View.GONE);
                }else {
                    mathematicsNoData.setVisibility(View.GONE);
                    mathematicsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    mathematicsDepartment.setHasFixedSize(true);
                    mathematicsDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list7,getContext());
                    mathematicsDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}