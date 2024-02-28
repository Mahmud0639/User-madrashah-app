clickListenerpackage com.manuni.usermadrasha.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manuni.usermadrasha.R;
import com.manuni.usermadrasha.ui.autoimageslider.autoimageadapter.AutoImageAdapter;
import com.manuni.usermadrasha.ui.autoimageslider.autoimagemodel.AutoImage;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private SliderView slider;
    private DatabaseReference databaseReference;
    private List<AutoImage> list;
    private AutoImageAdapter adapter;

    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("SliderImage");

        slider = view.findViewById(R.id.slider);
        slider.setIndicatorAnimation(IndicatorAnimationType.FILL);
        slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        slider.setScrollTimeInSec(3);
        slider.setIndicatorSelectedColor(Color.LTGRAY);
        slider.setIndicatorUnselectedColor(Color.GRAY);
        slider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        slider.setAutoCycle(true);
        slider.startAutoCycle();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    AutoImage data = dataSnapshot.getValue(AutoImage.class);
                    list.add(data);
                }
                adapter = new AutoImageAdapter(getContext(), list);
                slider.setSliderAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=546Q+HJ5 Gachbariya Bridge");
       Intent map = new Intent(Intent.ACTION_VIEW, uri);
       map.setPackage("com.google.android.apps.maps");
       startActivity(map);
    }
}