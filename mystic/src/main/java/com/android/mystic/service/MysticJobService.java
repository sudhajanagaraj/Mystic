package com.android.mystic.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;

import com.android.mystic.R;
import com.android.mystic.application.MysticMainActivity;
import com.android.mystic.log.MysticLog;

/**
 * Created by janagaraj.veluchamy on 6/27/2016.
 * This service executes each incoming job on a Handler running on your application's main thread.
 * This means that you must offload your execution logic to another thread/handler/AsyncTask of your choosing.
 */
public class MysticJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        MysticLog.d("[onStartJob] Started ... " );
        if(jobParameters != null) {
            MysticLog.d("[onStartJob] Executed Job Id :" + jobParameters.getJobId());
            ExecuteTask exeJob = new ExecuteTask(getApplicationContext(), jobParameters);
            exeJob.execute();
        }
        /*True if your service needs to process the work (on a separate thread).
        False if there's no more work to be done for this job.*/
        return true;
    }

/*  This method is called if the system has determined that you must stop execution of your job
    even before you've had a chance to call jobFinished(JobParameters, boolean).*/
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        MysticLog.d("[onStopJob] Started ... " );

        if(jobParameters != null) {
            MysticLog.d("[onStopJob] Executed Job Id :" + jobParameters.getJobId());
        }
        /*True to indicate to the JobManager whether you'd like to reschedule this job based on the retry criteria provided at job creation-time.
         False to drop the job. Regardless of the value returned, your job must stop executing.*/
        return false;
    }

    private void JobFinished(JobParameters params, boolean needsReschedule) {
     /* Callback to inform the JobManager you've finished executing. This can be called from any thread,
      as it will ultimately be run on your application's main thread.
      When the system receives this message it will release the wakelock being held. */
        if(params != null) {
            MysticLog.d("[onStopJob] Executed Job Id :" + params.getJobId());
            jobFinished(params, needsReschedule);
        }
    }


    class ExecuteTask extends AsyncTask<Void, Void, Void> {

        private JobParameters mParams = null;
        private Context mContext = null;
        ExecuteTask(Context context,JobParameters jobParams) {
            mContext = context;
            mParams = jobParams;
        }

        @Override
        protected Void doInBackground(Void... params) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
            builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Says").setContentText("This is a testing ");
            builder.setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

            Intent resultIntent = new Intent(mContext, MysticMainActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    mContext,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            builder.setContentIntent(resultPendingIntent);
            // Sets an ID for the notification
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, builder.build());
            MysticLog.d("[doInBackground] notified ");
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            JobFinished(mParams, false);
        }
    }
}
