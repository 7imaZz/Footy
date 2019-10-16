package com.example.footy.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.footy.Models.models.Teams.Player;
import com.example.footy.R;

import java.util.List;

public class PlayerAdapter extends BaseAdapter{

    private Context context;
    private List<Player> players;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Player getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.player_item, viewGroup, false);

        Player currentPlayer = players.get(i);

        TextView playerNameTextView = view.findViewById(R.id.tv_player_name);
        playerNameTextView.setText(currentPlayer.getPlayerName());

        TextView playerGoalsTextView = view.findViewById(R.id.tv_player_goals);
        playerGoalsTextView.setText(currentPlayer.getPlayerGoals());


        return view;
    }
}
