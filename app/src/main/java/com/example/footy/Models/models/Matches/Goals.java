package com.example.footy.Models.models.Matches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Goals {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("home_scorer")
    @Expose
    private String homeScorer;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("away_scorer")
    @Expose
    private String awayScorer;

    public Goals(String time, String homeScorer, String score, String awayScorer) {
        this.time = time;
        this.homeScorer = homeScorer;
        this.score = score;
        this.awayScorer = awayScorer;
    }

    public String getTime() {
        return time;
    }

    public String getHomeScorer() {
        return homeScorer;
    }

    public String getScore() {
        return score;
    }

    public String getAwayScorer() {
        return awayScorer;
    }
}
