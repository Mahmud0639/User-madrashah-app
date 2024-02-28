package com.manuni.usermadrasha.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.manuni.usermadrasha.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {
    private ViewPager pager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();

        list.add(new BranchModel(R.drawable.ic_desktop,"কম্পিউটার প্রশিক্ষণ","ইনশাআল্লাহ্‌ আমাদের মাদ্রাসায় কম্পিউটার প্রশিক্ষণ এর কোর্স খোলা হবে খুব শিগ্রই। প্রশিক্ষণ দিবেন একজন সফটওয়্যার ইঞ্জিনিয়ার(B. Sc. in Computer Science and Engineering)"));
        list.add(new BranchModel(R.drawable.ic_hefzo,"হিফজ বিভাগ","ইনশাআল্লাহ্ খুব শিগ্রই হিফজ বিভাগ খোলা হবে আমাদের মাদ্রাসায়।‌"));

        adapter = new BranchAdapter(getContext(),list);

        pager = view.findViewById(R.id.viewPager);
        pager.setAdapter(adapter);
        ImageView imageView = view.findViewById(R.id.madrasaPhoto);

        try {
            Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/gachbariya-madrasha.appspot.com/o/gallery%2F%5BB%401052942d.jpg1642526758072?alt=media&token=3f95c02e-e2e5-49bb-b06f-c1a6c6970b0c").placeholder(R.drawable.impl5).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }
}