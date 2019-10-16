
package com.example.footy.Models.models.Teams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coach {

    @SerializedName("coach_name")
    @Expose
    private String coachName;
    @SerializedName("coach_country")
    @Expose
    private String coachCountry;
    @SerializedName("coach_age")
    @Expose
    private String coachAge;

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachCountry() {
        return coachCountry;
    }

    public void setCoachCountry(String coachCountry) {
        this.coachCountry = coachCountry;
    }

    public String getCoachAge() {
        return coachAge;
    }

    public void setCoachAge(String coachAge) {
        this.coachAge = coachAge;
    }

}
