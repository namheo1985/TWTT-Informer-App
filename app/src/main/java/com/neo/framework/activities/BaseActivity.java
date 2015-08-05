package com.neo.framework.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.neo.framework.helpers.TaskHelper;

import org.twtt.twttinformer.R;


public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity{
    private ViewGroup mMainView;
    private int mResumeTimesCounter = 0;
    private static int FIRST_LOADED = 0;
    private static int SECOND_LOADED = 1;
    private int mIdMainLayout = 0;

    public ViewGroup getMainView() {
        return mMainView;
    }
    public int getIdMainLayout() {
        return mIdMainLayout;
    }
    @Override
    public void onDestroy() {
        onDestroy();
        recycle();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMainView = (ViewGroup) inflater.inflate(getIdMainLayout(), null);
        setContentView(mMainView);
        initiateUIFirst(mMainView);
    }
    public void initiateUIFirst(final View root) {
        TaskHelper.delayTask(new Runnable() {
            @Override
            public void run() {
                onLazyBuildingUI(root);
            }
        }, 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public abstract void onLazyBuildingUI(View root);
    public abstract void recycle();
    public abstract void onFirstResume();
    @Override
    public void onResume() {
        super.onResume();
        if (mResumeTimesCounter == FIRST_LOADED) {
            onFirstResume();
        }
        mResumeTimesCounter++;
    }
}
