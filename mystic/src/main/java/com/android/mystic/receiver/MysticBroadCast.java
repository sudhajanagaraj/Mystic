package com.android.mystic.receiver;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.mystic.application.MysticApp;
import com.android.mystic.data.MysticConstants;
import com.android.mystic.log.MysticLog;
import com.android.mystic.service.MysticJobService;

public class MysticBroadCast extends BroadcastReceiver {
    public MysticBroadCast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null && context != null) {
            Toast.makeText(context,"Phone connected !!!",Toast.LENGTH_LONG).show();
            JobScheduler mJobScheduler = (JobScheduler)
                    context.getSystemService( Context.JOB_SCHEDULER_SERVICE );

            JobInfo.Builder builder = new JobInfo.Builder(MysticConstants.NOTIFY_JOB_ID,
                    new ComponentName(context.getPackageName(),MysticJobService.class.getName()));
            builder.setMinimumLatency(5000);

            mJobScheduler.schedule(builder.build());
        }
    }
}
