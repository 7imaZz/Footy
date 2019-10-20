package com.example.footy;

public class Fav {

    private String teamLogo;
    private String teamName;
    private String userName;

    public Fav() {
    }

    public Fav(String teamLogo, String teamName, String userName) {
        this.teamLogo = teamLogo;
        this.teamName = teamName;
        this.userName = userName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getUserName() {
        return userName;
    }
}
