package com.example.footy.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footy.Models.models.Leagues.League;
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
import java.util.List;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder> {

    class LeagueViewHolder extends RecyclerView.ViewHolder {

        private TextView teamPositionTextView;
        private ImageView teamLogoImageView;
        private TextView teamNameTextView;
        private TextView mpTextView;
        private TextView wTextView;
        private TextView dTextView;
        private TextView lTextView;
        private TextView gfTextView;
        private TextView gaTextView;
        private TextView gdTextView;
        private TextView ptsTextView;

        LeagueViewHolder(@NonNull View itemView) {
            super(itemView);

            teamPositionTextView = itemView.findViewById(R.id.tv_team_pos);
            teamLogoImageView = itemView.findViewById(R.id.img_team_logo);
            teamNameTextView = itemView.findViewById(R.id.tv_team_name);
            mpTextView = itemView.findViewById(R.id.tv_mp);
            wTextView = itemView.findViewById(R.id.tv_w);
            dTextView = itemView.findViewById(R.id.tv_d);
            lTextView = itemView.findViewById(R.id.tv_l);
            gfTextView = itemView.findViewById(R.id.tv_gf);
            gaTextView = itemView.findViewById(R.id.tv_ga);
            gdTextView = itemView.findViewById(R.id.tv_gd);
            ptsTextView = itemView.findViewById(R.id.tv_pts);
        }
    }

    private Context context;
    private List<League> leagues;
    private String baseUrl = "https://apiv2.apifootball.com/?action=get_teams&team_id=";
    private String apiKey = "&APIkey=7876f9b8c95cd814f0d8110e8bdd381e298e8d7e62ba008cfa27bdf5a15046a7";

    public LeagueAdapter(Context context, List<League> leagues) {
        this.context = context;
        this.leagues = leagues;
    }

    @NonNull
    @Override
    public LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_item, parent, false);
        return new LeagueViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final LeagueViewHolder holder, int position) {

        final League currentLeague = leagues.get(position);

        if (currentLeague.getOverallLeaguePosition().length() == 1){
            currentLeague.setOverallLeaguePosition("0"+currentLeague.getOverallLeaguePosition());
        }

        holder.teamPositionTextView.setText(currentLeague.getOverallLeaguePosition());
        holder.teamNameTextView.setText(currentLeague.getTeamName());
        holder.mpTextView.setText(currentLeague.getOverallLeaguePayed());
        holder.wTextView.setText(currentLeague.getOverallLeagueW());
        holder.dTextView.setText(currentLeague.getOverallLeagueD());
        holder.lTextView.setText(currentLeague.getOverallLeagueL());
        holder.gfTextView.setText(currentLeague.getOverallLeagueGF());
        holder.gaTextView.setText(currentLeague.getOverallLeagueGA());
        holder.gdTextView.setText(Integer.parseInt(currentLeague.getOverallLeagueGF())-
                Integer.parseInt(currentLeague.getOverallLeagueGA())+"");
        holder.ptsTextView.setText(currentLeague.getOverallLeaguePTS());

        Picasso.with(context)
                .load("https://apiv2.apifootball.com//badges/"
                        +currentLeague.getTeamId()
                        +"_"+currentLeague.getTeamName().toLowerCase().replaceAll(" ","-")+".png")
                .placeholder(R.drawable.default_logo)
                .into(holder.teamLogoImageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        new logoAsyncTask().execute(new Temp(baseUrl+currentLeague.getTeamId()+apiKey, holder.teamLogoImageView));
                    }
                });

    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    @SuppressLint("StaticFieldLeak")
    public class logoAsyncTask extends AsyncTask<Temp, Void, Temp> {


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
