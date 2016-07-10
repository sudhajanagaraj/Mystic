package com.android.mystic.util;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.mystic.data.MysticConstants;
import com.android.mystic.log.MysticLog;
import com.android.mystic.service.MysticJobService;

import java.util.List;

/**
 * Created by janagaraj.veluchamy on 6/22/2016.
 */
public class Utility {

    public static boolean scheduleJobService(Context context, int jobId) {
        JobInfo jobInfo = new JobInfo.Builder(jobId,
                new ComponentName(context, MysticJobService.class))
                .setMinimumLatency(5000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(true)
                .build();
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (scheduler.schedule(jobInfo) == JobScheduler.RESULT_SUCCESS) {
            MysticLog.d("Job scheduled successfully!");
            return true;
        }
        return false;
    }

    public static void cancelJobService(Context context, int jobId) {
        JobScheduler scheduler =  (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        List<JobInfo> lists = scheduler.getAllPendingJobs();
        if (lists != null) {
            MysticLog.d("Cancel scheduled job : " + jobId);
            scheduler.cancel(jobId);
        }
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr != null) {
            NetworkInfo nwInfo = connMgr.getActiveNetworkInfo();
            if (nwInfo != null && nwInfo.isAvailable() && nwInfo.isConnected()) {
                MysticLog.d("Network is availabe and Connected");
                return true;
            }
        }
        return true;
    }
}
