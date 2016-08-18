package com.jonathan.james.eric.project_3;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;

/**
 * Created by jamesrondina on 8/18/16.
 */
public class NotifyService extends JobService {

    private AsyncTask mTask;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //Implement task in here

        mTask = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {

                notification();
                return null;
            }
        };
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        if(mTask != null && mTask.getStatus().equals(AsyncTask.Status.RUNNING)){
            mTask.cancel(false);
        }

        return true;
    }

    public void notification() {

        APIServices apiService = new APIServices();

        //Get the first article in Top News
        Article breakingNews =
                apiService.topNews("home", apiService.retrofitInit(getApplicationContext())).get(0);

        Intent intent = new Intent(this, SectionPageAdapterActivity.class);// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        //Build Notification for breaking news
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(breakingNews.getHeadline())
                .setContentText(breakingNews.getLeadParagraph())
                .setContentIntent(pIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .build();
    }
}
