package com.jonathan.james.eric.project_3;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.jonathan.james.eric.project_3.interfaces.APICallback;

import java.util.List;

/**
 * Created by jamesrondina on 8/18/16.
 */
public class NotifyService extends JobService implements APICallback {


    APIServices apiServices;
    private String mHeadline;

    @Override
    public void responseFinished(List<Article> responseList) {

        mHeadline = responseList.get(0).getHeadline();

        //build notification

        Intent intent = new Intent(this, SectionPageAdapterActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        mBuilder.setContentTitle("Top News");
        mBuilder.setContentText(mHeadline);
        Log.d("NOTIFY", "onStartJob: " + mHeadline);
        mBuilder.setContentIntent(pIntent);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());

    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        apiServices = new APIServices();
        apiServices.topNews("home", apiServices.retrofitInit(getApplicationContext()),this);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
