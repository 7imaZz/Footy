
package com.example.footy.Models.models.Matches;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home {

    @SerializedName("starting_lineups")
    @Expose
    private List<Object> startingLineups = null;
    @SerializedName("substitutes")
    @Expose
    private List<Object> substitutes = null;
    @SerializedName("coach")
    @Expose
    private List<Object> coach = null;
    @SerializedName("missing_players")
    @Expose
    private List<Object> missingPlayers = null;

    public List<Object> getStartingLineups() {
        return startingLineups;
    }

    public void setStartingLineups(List<Object> startingLineups) {
        this.startingLineups = startingLineups;
    }

    public List<Object> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Object> substitutes) {
        this.substitutes = substitutes;
    }

    public List<Object> getCoach() {
        return coach;
    }

    public void setCoach(List<Object> coach) {
        this.coach = coach;
    }

    public List<Object> getMissingPlayers() {
        return missingPlayers;
    }

    public void setMissingPlayers(List<Object> missingPlayers) {
        this.missingPlayers = missingPlayers;
    }

}
