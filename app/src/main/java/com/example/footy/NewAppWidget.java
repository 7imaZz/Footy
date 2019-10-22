package com.example.footy;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class NewAppWidget extends AppWidgetProvider {


    private static final String SHARED_PREFS = "prefs";
    private static final String HOME_GOALS = "homeGoals";
    private static final String AWAY_GOALS = "awayGoals";
    private static final String MID = "mid";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int i=0; i<appWidgetIds.length; i++){

            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent, 0);

            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String homeGoals = prefs.getString(HOME_GOALS, "Home");
            String awayGoals = prefs.getString(AWAY_GOALS, "Away");
            String mid = prefs.getString(MID, "Mid");

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setOnClickPendingIntent(R.id.ll_wid, pendingIntent);

            views.setCharSequence(R.id.tv_home_team_name_wid, "setText", homeGoals);
            views.setCharSequence(R.id.tv_away_team_name_wid, "setText", awayGoals);
            views.setCharSequence(R.id.tv_time_wid, "setText", mid);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

