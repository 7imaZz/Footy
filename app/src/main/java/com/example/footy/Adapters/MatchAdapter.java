package com.example.footy.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footy.Models.models.Matches.Match;
import com.example.footy.Models.models.Teams.TeamModel;
import com.example.footy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.SubMatchViewHolder>{

    class SubMatchViewHolder extends RecyclerView.ViewHolder{

        private TextView leagueNameTextView;
        private TextView homeTeamNameTextView;
        private TextView awayTeamNameTextView;
        private TextView matchTimeTextView;
        private ImageView homeTeamImg;
        private ImageView awayTeamImg;

        SubMatchViewHolder(@NonNull View itemView) {
            super(itemView);
            leagueNameTextView = itemView.findViewById(R.id.tv_league_name);
            homeTeamNameTextView = itemView.findViewById(R.id.tv_home_team_name);
            awayTeamNameTextView = itemView.findViewById(R.id.tv_away_team_name);
            matchTimeTextView = itemView.findViewById(R.id.tv_time);
            homeTeamImg = itemView.findViewById(R.id.img_home_team_logo);
            awayTeamImg = itemView.findViewById(R.id.img_away_team_logo);
        }
    }

    private Context context;
    private List<Match> matches;
    private List<TeamModel> homeTeams;
    private List<TeamModel> awayTeams;

    public MatchAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    public MatchAdapter(Context context, List<Match> matches, List<TeamModel> teamModels, List<TeamModel> awayTeams) {
        this.context = context;
        this.matches = matches;
        this.homeTeams = teamModels;
        this.awayTeams = awayTeams;
    }



    @NonNull
    @Override
    public SubMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_item, parent, false);
        return new SubMatchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SubMatchViewHolder holder, int position) {

        Match currentMatch = matches.get(position);

        holder.leagueNameTextView.setText(currentMatch.getCountryName()+" - "+currentMatch.getLeagueName());

        holder.homeTeamNameTextView.setText(currentMatch.getMatchHometeamName());
        holder.awayTeamNameTextView.setText(currentMatch.getMatchAwayteamName());
        if (currentMatch.getMatchStatus().equals("Finished")){
            holder.matchTimeTextView.setText(currentMatch.getMatchHometeamScore()+" - "+currentMatch.getMatchAwayteamScore());
        }else {
            holder.matchTimeTextView.setText(currentMatch.getMatchTime());
        }

        holder.homeTeamImg.setImageResource(R.drawable.default_logo);
        holder.awayTeamImg.setImageResource(R.drawable.default_logo);
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
