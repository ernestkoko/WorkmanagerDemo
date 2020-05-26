package com.ernest.workmanagerdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackgroundWorker extends Worker {
    public  final String  NOTIFICATION_ID = "simplified_coding";
    public  final String  CHANNEL_NAME = "example_coding";

    // constructor
    public BackgroundWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        displayNotification("Hey I am your work", "Work is finished");


        return Result.success();
    }



    //display notification

    /**
     * method displays notification
     * @param task the task
     * @param desc is the description of the notification
     */
    public void displayNotification(String task, String desc){

        //create notification manager
        NotificationManager manager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        //create notification channel for android O or above
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            //create an object of the notification channel class
            //give it an id and channel name and the importance
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            //create the channel
            manager.createNotificationChannel(channel);
        }
        //create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                NOTIFICATION_ID)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);

        //use manager object to display the notification
        manager.notify(1, builder.build());
    }
}
