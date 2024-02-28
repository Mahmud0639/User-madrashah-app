package com.manuni.usermadrasha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;


import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;

    private final String CHECKEDITEM = "checked_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        switch (getCheckedItem()) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;


        }


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Start, R.string.Close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_developer:
                startActivity(new Intent(this, DeveloperActivity.class));
                break;
            case R.id.navigation_video:
                Uri link = Uri.parse("https://www.youtube.com/channel/UC805GUo2uYHz-TBmd4B45fQ");
                Intent myIntent = new Intent(Intent.ACTION_VIEW, link);
                try {
                    startActivity(myIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.navigation_rate:
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.navigation_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");

                String text = "গাছবাড়িয়া মাদ্রাসা";
                String body = "এই অ্যাপের মাধ্যমে আপনারা এই মাদ্রাসার সকল তথ্য সঠিকভাবে এবং সময়মত জানতে পারবেন। \nআমাদের অ্যাপটি প্লেস্টোর থেকে ডাউনলোড করার জন্য নিচের লিংকে ক্লিক করুনঃ \ncom.manuni.usermadrasha";

                intent.putExtra(Intent.EXTRA_SUBJECT, text);
                intent.putExtra(Intent.EXTRA_TEXT, body);

                startActivity(Intent.createChooser(intent, "Share with"));

                break;
            case R.id.navigation_theme:
                showDialog();
                break;
        }
        return true;
    }

    private void showDialog() {
        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select a theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    selected = themes[which];
                    checkedItem = which;

            }
        });

        try {
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                        if (selected == null) {
                            selected = themes[which];
                            checkedItem = which;
                        }

                    switch (selected) {
                        case "System Default":
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();

                            break;

                        case "Dark Theme":
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();

                            break;
                        case "Light Theme":
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                            break;
                    }

                    setCheckedItem(checkedItem);



                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private int getCheckedItem() {
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }

    private void setCheckedItem(int i) {
        editor.putInt(CHECKEDITEM, i);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}