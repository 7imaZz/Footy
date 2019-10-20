package com.example.footy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.footy.Adapters.LeagueAdapter;
import com.example.footy.Models.models.Leagues.League;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaguesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<League> leagues = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rv_standing);

        recyclerView.setLayoutManager(new LinearLayoutManager(LeaguesActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(LeaguesActivity.this, DividerItemDecoration.VERTICAL));


        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String leagueId = "";
        if (getIntent() != null){
            leagueId = getIntent().getStringExtra("league_id");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiv2.apifootball.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApi footballApi = retrofit.create(FootballApi.class);

        Call<List<League>> call = footballApi.getStandings(leagueId, getString(R.string.API_KEY));

        call.enqueue(new Callback<List<League>>() {
            @Override
            public void onResponse(@NonNull Call<List<League>> call, @NonNull Response<List<League>> response) {

                leagues = response.body();

                LeagueAdapter adapter = new LeagueAdapter(LeaguesActivity.this, leagues);
                recyclerView.setAdapter(adapter);
                setTitle(leagues.get(0).getLeagueName());
            }

            @Override
            public void onFailure(@NonNull Call<List<League>> call, @NonNull Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_pl){
            Intent intent = new Intent(LeaguesActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "148");
            startActivity(intent);
            finish();
        }else if (id == R.id.menu_sp){
            Intent intent = new Intent(LeaguesActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "468");
            startActivity(intent);
            finish();
        }else if (id == R.id.menu_ger){
            Intent intent = new Intent(LeaguesActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "195");
            startActivity(intent);
            finish();
        }else if (id == R.id.menu_eg){
            Intent intent = new Intent(LeaguesActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "144");
            startActivity(intent);
            finish();
        }else if (id == R.id.menu_fr){
            Intent intent = new Intent(LeaguesActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "176");
            startActivity(intent);
            finish();
        }else if (id == R.id.menu_it){
            Intent intent = new Intent(LeaguesActivity.this, LeaguesActivity.class);
            intent.putExtra("league_id", "262");
            startActivity(intent);
            finish();
        }else if (id == R.id.menu_fav){
            Intent intent = new Intent(LeaguesActivity.this, FavouriteActivity.class);
            startActivity(intent);
        }else if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
