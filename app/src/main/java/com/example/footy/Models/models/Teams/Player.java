
package com.example.footy.Models.models.Teams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("player_key")
    @Expose
    private Long playerKey;
    @SerializedName("player_name")
    @Expose
    private String playerName;
    @SerializedName("player_number")
    @Expose
    private String playerNumber;
    @SerializedName("player_country")
    @Expose
    private String playerCountry;
    @SerializedName("player_type")
    @Expose
    private String playerType;
    @SerializedName("player_age")
    @Expose
    private String playerAge;
    @SerializedName("player_match_played")
    @Expose
    private String playerMatchPlayed;
    @SerializedName("player_goals")
    @Expose
    private String playerGoals;
    @SerializedName("player_yellow_cards")
    @Expose
    private String playerYellowCards;
    @SerializedName("player_red_cards")
    @Expose
    private String playerRedCards;

    public Long getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(Long playerKey) {
        this.playerKey = playerKey;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(String playerCountry) {
        this.playerCountry = playerCountry;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public String getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(String playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlayerMatchPlayed() {
        return playerMatchPlayed;
    }

    public void setPlayerMatchPlayed(String playerMatchPlayed) {
        this.playerMatchPlayed = playerMatchPlayed;
    }

    public String getPlayerGoals() {
        return playerGoals;
    }

    public void setPlayerGoals(String playerGoals) {
        this.playerGoals = playerGoals;
    }

    public String getPlayerYellowCards() {
        return playerYellowCards;
    }

    public void setPlayerYellowCards(String playerYellowCards) {
        this.playerYellowCards = playerYellowCards;
    }

    public String getPlayerRedCards() {
        return playerRedCards;
    }

    public void setPlayerRedCards(String playerRedCards) {
        this.playerRedCards = playerRedCards;
    }

}
