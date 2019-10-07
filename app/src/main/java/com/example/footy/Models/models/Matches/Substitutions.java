
package com.example.footy.Models.models.Matches;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Substitutions {

    @SerializedName("home")
    @Expose
    private List<Object> home = null;
    @SerializedName("away")
    @Expose
    private List<Object> away = null;

    public List<Object> getHome() {
        return home;
    }

    public void setHome(List<Object> home) {
        this.home = home;
    }

    public List<Object> getAway() {
        return away;
    }

    public void setAway(List<Object> away) {
        this.away = away;
    }

}
