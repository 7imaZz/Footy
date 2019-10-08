package com.example.footy.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footy.Models.models.Matches.Away;
import com.example.footy.Models.models.Matches.Goals;
import com.example.footy.Models.models.Matches.Home;
import com.example.footy.Models.models.Matches.Lineup;
import com.example.footy.Models.models.Matches.Match;
import com.example.footy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.SubMatchViewHolder>{

    class SubMatchViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView leagueNameTextView;
        private TextView homeTeamNameTextView;
        private TextView awayTeamNameTextView;
        private TextView matchTimeTextView;
        private ImageView homeTeamImg;
        private ImageView awayTeamImg;
        private ProgressBar homeProgressBar;
        private ProgressBar awayProgressBar;
        private LinearLayout moreDetails;
        private TextView homeGoalsTextView;
        private TextView awayGoalsTextView;
        private TextView homeLineupTextView;
        private TextView awayLineupTextView;

        SubMatchViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_match);
            leagueNameTextView = itemView.findViewById(R.id.tv_league_name);
            homeTeamNameTextView = itemView.findViewById(R.id.tv_home_team_name);
            awayTeamNameTextView = itemView.findViewById(R.id.tv_away_team_name);
            matchTimeTextView = itemView.findViewById(R.id.tv_time);
            homeTeamImg = itemView.findViewById(R.id.img_home_team_logo);
            awayTeamImg = itemView.findViewById(R.id.img_away_team_logo);
            homeProgressBar = itemView.findViewById(R.id.pb_loading_home);
            awayProgressBar = itemView.findViewById(R.id.pb_loading_away);
            moreDetails = itemView.findViewById(R.id.ll_more);
            homeGoalsTextView = itemView.findViewById(R.id.tv_home_goals);
            awayGoalsTextView = itemView.findViewById(R.id.tv_away_goals);
            homeLineupTextView = itemView.findViewById(R.id.tv_home_lineup);
            awayLineupTextView = itemView.findViewById(R.id.tv_away_lineup);
        }
    }

    private Context context;
    private List<Match> matches;
    private ArrayList<Integer> flags = new ArrayList<>();

    public MatchAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }


    @NonNull
    @Override
    public SubMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_item, parent, false);
        return new SubMatchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SubMatchViewHolder holder, final int position) {

        final Match currentMatch = matches.get(position);
        List<Goals> matchGoals = currentMatch.getGoalscorer();
        Lineup matchLineup = currentMatch.getLineup();
        Home homeLineup = matchLineup.getHome();
        Away awayLineup = matchLineup.getAway();
        StringBuilder homeGoals = new StringBuilder();
        StringBuilder awayGoals = new StringBuilder();


        for (int i=0; i<matchGoals.size(); i++){
            if (!matchGoals.get(i).getHomeScorer().equals("")){
                homeGoals.append(matchGoals.get(i).getTime()).append("': ").append(matchGoals.get(i).getHomeScorer()).append("\n");
            }else if (!matchGoals.get(i).getAwayScorer().equals("")){
                awayGoals.append(matchGoals.get(i).getTime()).append("': ").append(matchGoals.get(i).getAwayScorer()).append("\n");
            }
        }

        for (int i=0; i<matches.size(); i++){
            flags.add(i, 0);
        }

        holder.leagueNameTextView.setText(currentMatch.getCountryName()+" - "+currentMatch.getLeagueName());
        holder.homeTeamNameTextView.setText(currentMatch.getMatchHometeamName());
        holder.awayTeamNameTextView.setText(currentMatch.getMatchAwayteamName());
        holder.homeGoalsTextView.setText(homeGoals.toString());
        holder.awayGoalsTextView.setText(awayGoals.toString());
        holder.homeLineupTextView.setText(homeLineup.getStartingLineups().toString());
        holder.awayLineupTextView.setText(awayLineup.getStartingLineups().toString());

        if (currentMatch.getMatchStatus().equals("Finished")){
            holder.matchTimeTextView.setText(currentMatch.getMatchHometeamScore()+" - "+currentMatch.getMatchAwayteamScore());
        }else {
            holder.matchTimeTextView.setText(currentMatch.getMatchTime());
        }

        String homeTeamName = currentMatch.getMatchHometeamName().toLowerCase().replaceAll(" ","-");
        String homeTeamId = currentMatch.getMatchHometeamId();
        String awayTeamName = currentMatch.getMatchAwayteamName().toLowerCase().replaceAll(" ","-");
        String awayTeamId = currentMatch.getMatchAwayteamId();


        Picasso.with(context)
                .load("https://apiv2.apifootball.com//badges/"+homeTeamId+"_"+homeTeamName+".png")
                .placeholder(R.drawable.default_logo)
                .into(holder.homeTeamImg, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.homeProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        Picasso.with(context)
                .load("https://apiv2.apifootball.com//badges/"+awayTeamId+"_"+awayTeamName+".png")
                .placeholder(R.drawable.default_logo)
                .into(holder.awayTeamImg, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.awayProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flags.get(position)==0) {
                    flags.set(position, 1);
                    holder.moreDetails.setVisibility(View.VISIBLE);
                }else {
                    holder.moreDetails.setVisibility(View.GONE);
                    flags.set(position, 0);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

}
