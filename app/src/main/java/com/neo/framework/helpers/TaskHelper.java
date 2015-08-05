package com.neo.framework.helpers;

import android.os.Handler;

public class TaskHelper {
    private static Handler handler;
    public static void delayTask(Runnable runner, int delayedTime) {
        handler = new Handler();
        handler.postDelayed(runner, delayedTime);
    }

    public static void deleteHandlerOfDelayedTask(Runnable runner) {
        if (handler != null) handler.removeCallbacks(runner);
        handler = null;
    }
}
