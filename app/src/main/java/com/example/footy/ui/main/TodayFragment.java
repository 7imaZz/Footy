package com.example.footy.ui.main;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.footy.Adapters.MatchAdapter;
import com.example.footy.Constants;
import com.example.footy.FootballApi;
import com.example.footy.Models.models.Matches.Match;
import com.example.footy.Models.models.Teams.TeamModel;
import com.example.footy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        recyclerView = view.findViewById(R.id.rv_matches);
        progressBar = view.findViewById(R.id.pb_loading_matches);


        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiv2.apifootball.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApi footballApi = retrofit.create(FootballApi.class);

        Call<List<Match>> call = footballApi.getMatches("2019-10-6", "2019-10-6", Constants.LEAGUES_IDS, getString(R.string.API_KEY));

        call.enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                matches = response.body();

                MatchAdapter adapter = new MatchAdapter(getActivity(), matches);

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
            }
        });



        return view;
    }

}
