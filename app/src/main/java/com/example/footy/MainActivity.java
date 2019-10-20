package com.example.footy;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import com.example.footy.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(4);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_pl){
            Intent intent = new Intent(MainActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "148");
            startActivity(intent);
        }else if (id == R.id.menu_sp){
            Intent intent = new Intent(MainActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "468");
            startActivity(intent);
        }else if (id == R.id.menu_ger){
            Intent intent = new Intent(MainActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "195");
            startActivity(intent);
        }else if (id == R.id.menu_eg){
            Intent intent = new Intent(MainActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "144");
            startActivity(intent);
        }else if (id == R.id.menu_fr){
            Intent intent = new Intent(MainActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "176");
            startActivity(intent);
        }else if (id == R.id.menu_it){
            Intent intent = new Intent(MainActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "262");
            startActivity(intent);
        }else if (id == R.id.menu_fav){
            Intent intent = new Intent(MainActivity.this, FavouriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}