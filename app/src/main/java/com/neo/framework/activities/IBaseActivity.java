package com.neo.framework.activities;

import android.view.View;

public interface IBaseActivity {
    public int getIdMainLayout();

    public void onLazyBuildingUI(View root);

    public void initiateUIFirst(final View root);

    public void recycle();

    public void onFirstResume();
}
