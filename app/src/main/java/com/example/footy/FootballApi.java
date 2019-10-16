package com.example.footy;

import com.example.footy.Models.models.Leagues.League;
import com.example.footy.Models.models.Matches.Match;
import com.example.footy.Models.models.Teams.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FootballApi {

    @GET("?action=get_events")
    Call<List<Match>> getMatches(@Query("from") String from, @Query("to") String to, @Query("league_id") String id,@Query("APIkey") String api);

    @GET("?action=get_teams")
    Call<List<TeamModel>> getTeams(@Query("team_id")String teamId, @Query("APIkey") String APIkey);

    @GET("?action=get_standings")
    Call<List<League>> getStandings(@Query("league_id")String leagueId, @Query("APIkey") String APIkey);
}
