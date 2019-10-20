package com.example.footy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footy.Fav;
import com.example.footy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavViewHolder>{

    class FavViewHolder extends RecyclerView.ViewHolder{

        private TextView userName;
        private TextView teamName;
        private ImageView teamLogo;

        FavViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_user_name);
            teamName = itemView.findViewById(R.id.tv_fav_team);
            teamLogo = itemView.findViewById(R.id.img_fav_logo);
        }
    }

    private Context context;
    private List<Fav> favs;

    public FavouriteAdapter(Context context, List<Fav> favs) {
        this.context = context;
        this.favs = favs;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {

        Fav currentFav = favs.get(position);

        holder.teamName.setText(currentFav.getTeamName());
        holder.userName.setText(currentFav.getUserName());

        Picasso.with(context)
                .load(currentFav.getTeamLogo())
                .placeholder(R.drawable.default_logo)
                .into(holder.teamLogo);
    }

    @Override
    public int getItemCount() {
        return favs.size();
    }
}
