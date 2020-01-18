package com.epluribusneo.thecoinwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.content.ComponentName;
import android.widget.Toast;

import java.util.Random;


public class Widget extends AppWidgetProvider{

    private static final String SYNC_CLICKED = "WidgetImageClick";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Random rand = new Random();
        RemoteViews remoteViews;
        ComponentName componentName;

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        componentName = new ComponentName(context, Widget.class);
        remoteViews.setImageViewResource(R.id.imageView, setImage(rand.nextInt(2), context));
        remoteViews.setOnClickPendingIntent(R.id.imageView, getPendingSelfIntent(context, SYNC_CLICKED));
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(SYNC_CLICKED.equals(intent.getAction())){
            Random rand = new Random();
            RemoteViews remoteViews;
            ComponentName componentName;
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            componentName = new ComponentName(context, Widget.class);
            remoteViews.setImageViewResource(R.id.imageView, setImage(rand.nextInt(2), context));
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action){
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context,0,intent,0);
    }

    public int setImage(int num, Context contxt){
        int image = 0;
        String strCoin = ". . .";
        switch (num){
            case 0:
                image = R.drawable.coin1;
                strCoin = "Орёл";
                break;
            case 1:
                image = R.drawable.coin2;
                strCoin = "Решка";
                break;
        }
        Toast.makeText(contxt, strCoin, Toast.LENGTH_SHORT).show();
        return  image;
    }
}