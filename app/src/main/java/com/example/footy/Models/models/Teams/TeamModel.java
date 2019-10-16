
package com.example.footy.Models.models.Teams;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamModel {

    @SerializedName("team_key")
    @Expose
    private String teamKey;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("team_badge")
    @Expose
    private String teamBadge;
    @SerializedName("players")
    @Expose
    private List<Player> players = null;
    @SerializedName("coaches")
    @Expose
    private List<Coach> coaches = null;

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamBadge() {
        return teamBadge;
    }

    public void setTeamBadge(String teamBadge) {
        this.teamBadge = teamBadge;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

}
