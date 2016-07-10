package com.android.mystic.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.BuildConfig;
import android.util.Log;


/**
 * Created by janagaraj.veluchamy on 6/22/2016.
 */
public class MysticLog {

    private static final String sAppTag = "Mystic";

    private static String sAppVersion = BuildConfig.VERSION_NAME;

    private static boolean sDebugMode = true;

    private static final int INITIAL_STACK_COUNT = 3;

    public static void init(Context context) {
        PackageManager pkg = context.getPackageManager();
        try {
            PackageInfo info = pkg.getPackageInfo(context.getPackageName(), 0);
            sAppVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        setDebugMode(true);
    }

    private static int getProperStackCount(StackTraceElement[] stackTraceElements) {
        int size = stackTraceElements.length;
        int stackCount = INITIAL_STACK_COUNT;
        for (int i = 0; i < size - 1; i++) {
            if (stackTraceElements[stackCount].getMethodName().equals(
                    stackTraceElements[stackCount + 1].getMethodName())) {
                stackCount = stackCount + 1;
            } else {
                return stackCount + 1;
            }
        }
        return INITIAL_STACK_COUNT;
    }

    public static void setDebugMode(boolean debugMode) {
        sDebugMode = debugMode;
    }

    public static boolean getDebugMode( ) {
        return sDebugMode;
    }

    public static String getCallerInfo() {
        String info = " ";

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int stackCount = getProperStackCount(stackTraceElements);
        StackTraceElement ste = stackTraceElements[stackCount];

        String fileName = ste.getFileName();
        String methodName = ste.getMethodName();
        int lineNumber = ste.getLineNumber();
        info = "(at " + fileName + " " + methodName + " " + Integer.toString(lineNumber) + ")";

        return info;
    }

    public static void i(String log) {
        if (sDebugMode) {
            Log.i(sAppTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void d(String log) {
        if (sDebugMode) {
            Log.d(sAppTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void v(String log) {
        if (sDebugMode) {
            Log.v(sAppTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void w(String log) {
        if (sDebugMode) {
            Log.w(sAppTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void e(String log) {
        if (sDebugMode) {
            Log.e(sAppTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void x(String log) {
        Log.i(sAppTag, log + " [v:" + sAppVersion + "]");
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    public static void i(String sTag, String log) {
        if (sDebugMode) {
            Log.i(sTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void d(String sTag, String log) {
        if (sDebugMode) {
            Log.d(sTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void v(String sTag, String log) {
        if (sDebugMode) {
            Log.v(sTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void w(String sTag, String log) {
        if (sDebugMode) {
            Log.w(sTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void e(String sTag, String log) {
        if (sDebugMode) {
            Log.e(sTag, log + " " + getCallerInfo() + "[v:" + sAppVersion + "]");
        }
    }

    public static void x(String sTag, String log) {
        Log.i(sTag, log + " [v:" + sAppVersion + "]");
    }

    /////////////////////////////////////////////////////////////////////////////////////////


}
