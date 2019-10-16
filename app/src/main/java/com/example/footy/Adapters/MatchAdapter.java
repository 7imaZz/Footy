package com.example.footy.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
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
import com.example.footy.Temp;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private String baseUrl = "https://apiv2.apifootball.com/?action=get_teams&team_id=";
    private String apiKey = "&APIkey=7876f9b8c95cd814f0d8110e8bdd381e298e8d7e62ba008cfa27bdf5a15046a7";

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

        String homePlayers = homeLineup.getStartingLineups().toString()
                .replaceAll("lineup_player","")
                .replaceAll("lineup_number","")
                .replaceAll("lineup_position", "")
                .replaceAll("=", "")
                .replaceAll("\\{","")
                .replaceAll("\\}","")
                .replaceAll("\\[","")
                .replaceAll("\\]","")
                .replaceAll("\\d","")
                .replaceAll(","," ")
                .replaceAll("    ",",")
                .replaceAll(", ","\n");

        String awayPlayers = awayLineup.getStartingLineups().toString()
                .replaceAll("lineup_player","")
                .replaceAll("lineup_number","")
                .replaceAll("lineup_position", "")
                .replaceAll("=", "")
                .replaceAll("\\{","")
                .replaceAll("\\}","")
                .replaceAll("\\[","")
                .replaceAll("\\]","")
                .replaceAll("\\d","")
                .replaceAll(","," ")
                .replaceAll("    ",",")
                .replace(", ","\n");

        holder.leagueNameTextView.setText(currentMatch.getCountryName()+" - "+currentMatch.getLeagueName());
        holder.homeTeamNameTextView.setText(currentMatch.getMatchHometeamName());
        holder.awayTeamNameTextView.setText(currentMatch.getMatchAwayteamName());
        holder.homeGoalsTextView.setText(homeGoals.toString());
        holder.awayGoalsTextView.setText(awayGoals.toString());
        holder.homeLineupTextView.setText(homePlayers);
        holder.awayLineupTextView.setText(awayPlayers);

        if (currentMatch.getMatchStatus().equals("Finished")){
            holder.matchTimeTextView.setText(currentMatch.getMatchHometeamScore()+" - "+currentMatch.getMatchAwayteamScore());
        }else {
            if (currentMatch.getMatchLive().equals("0")) {
                holder.matchTimeTextView.setText(currentMatch.getMatchTime());
            }else {
                holder.matchTimeTextView.setTextSize(12);
                holder.matchTimeTextView.setText("(Live "+currentMatch.getMatchStatus().replaceAll(" ","")+"')\n"+currentMatch.getMatchHometeamScore()+" - "+currentMatch.getMatchAwayteamScore());
            }
        }

        String homeTeamName = currentMatch.getMatchHometeamName().toLowerCase().replaceAll(" ","-");
        final String homeTeamId = currentMatch.getMatchHometeamId();
        String awayTeamName = currentMatch.getMatchAwayteamName().toLowerCase().replaceAll(" ","-");
        final String awayTeamId = currentMatch.getMatchAwayteamId();


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
                        holder.homeProgressBar.setVisibility(View.GONE);
                        new logoAsyncTask().execute(new Temp(baseUrl+homeTeamId+apiKey, holder.homeTeamImg));

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
                        holder.awayProgressBar.setVisibility(View.GONE);
                        new logoAsyncTask().execute(new Temp(baseUrl+awayTeamId+apiKey, holder.awayTeamImg));
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


    @SuppressLint("StaticFieldLeak")
    public class logoAsyncTask extends AsyncTask<Temp, Void, Temp>{


        @Override
        protected Temp doInBackground(Temp... jsonUrl) {

            String s;
            String res = "";
            try {

                URL url = new URL(jsonUrl[0].getImageUrl());

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(15000);
                urlConnection.connect();


                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                s = stream2String(inputStream);

                res = extractFromJson(s);

                urlConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return new Temp(res, jsonUrl[0].getImageView());
        }

        @Override
        protected void onPostExecute(Temp temp) {
            Picasso.with(context)
                    .load(temp.getImageUrl())
                    .placeholder(R.drawable.default_logo)
                    .into(temp.getImageView());
        }
    }

    private String stream2String(InputStream inputStream){

        String line;
        StringBuilder text = new StringBuilder();

        BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream));

        try{
            while((line = reader.readLine()) != null){
                text.append(line);
            }
        }catch (IOException ignored){}

        return text.toString();
    }

    private String extractFromJson(String json){
        try {

            JSONArray root = new JSONArray(json);
            JSONObject first = root.getJSONObject(0);
            return first.getString("team_badge");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
