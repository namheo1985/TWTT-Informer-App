package com.neo.framework.helpers;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

public class UIHelper {
    public static void removeAllViewsOf(ViewGroup view) {
        if (view == null || view.getChildCount() == 0) return;
        for (int i = 0; i < view.getChildCount(); i++) {
            View itemView = view.getChildAt(i);
            if (itemView instanceof ViewGroup) {
                removeAllViewsOf((ViewGroup) itemView);
                ((ViewGroup) itemView).removeAllViews();
            }
        }
        view.removeAllViews();
    }

}
