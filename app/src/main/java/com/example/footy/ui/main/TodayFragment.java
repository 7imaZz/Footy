package com.example.footy.ui.main;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.footy.Adapters.MatchAdapter;
import com.example.footy.Constants;
import com.example.footy.FootballApi;
import com.example.footy.Models.models.Matches.Match;
import com.example.footy.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {


    public TodayFragment() {
        // Required empty public constructor
    }


    private List<Match> matches = new ArrayList<>();

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView noMatchesTextView;


    private static final String SHARED_PREFS = "prefs";
    private static final String HOME_GOALS = "homeGoals";
    private static final String AWAY_GOALS = "awayGoals";
    private static final String MID = "mid";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_today, container, false);



        SwipeRefreshLayout refreshLayout = view.findViewById(R.id.refresh);
        recyclerView = view.findViewById(R.id.rv_matches);
        progressBar = view.findViewById(R.id.pb_loading_matches);
        noMatchesTextView = view.findViewById(R.id.tv_no_matches);

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiv2.apifootball.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApi footballApi = retrofit.create(FootballApi.class);

        Call<List<Match>> call = footballApi.getMatches(date, date, Constants.LEAGUES_IDS, getString(R.string.API_KEY));

        call.enqueue(new Callback<List<Match>>() {

            @Override
            public void onResponse(@NonNull Call<List<Match>> call, @NonNull Response<List<Match>> response) {
                matches = response.body();

                MatchAdapter adapter = new MatchAdapter(getActivity(), matches);

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

                confirmConfiguration();
            }

            @Override
            public void onFailure(@NonNull Call<List<Match>> call, @NonNull Throwable t) {
                noMatchesTextView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                assert getFragmentManager() != null;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (Build.VERSION.SDK_INT >= 26) {
                    ft.setReorderingAllowed(false);
                }
                ft.detach(TodayFragment.this).attach(TodayFragment.this).commit();
            }
        });



        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void confirmConfiguration(){

        String homeGoals = matches.get(0).getMatchHometeamName();
        String awayGoals = matches.get(0).getMatchAwayteamName();
        String mid;

        if (matches.get(0).getMatchStatus().equals(getActivity().getString(R.string.finished))){
            mid = matches.get(0).getMatchHometeamScore()+" - "+matches.get(0).getMatchAwayteamScore();
        }else {
            if (matches.get(0).getMatchLive().equals("0")){
                mid = matches.get(0).getMatchTime();
            }else {
                mid = "("+getActivity().getString(R.string.live)+" "+matches.get(0).getMatchStatus().replaceAll(" ","")+"')\n"+matches.get(0).getMatchHometeamScore()+" - "+matches.get(0).getMatchAwayteamScore();
            }
        }

        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(HOME_GOALS, homeGoals);
        editor.putString(AWAY_GOALS, awayGoals);
        editor.putString(MID, mid);
        editor.apply();

    }

}
