package com.neo.framework.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.neo.framework.helpers.TaskHelper;

import org.twtt.twttinformer.R;


public abstract class ToolbarActivity extends BaseActivity {
    private Toolbar mToolbar;
    public Toolbar getToolbar() {
        return mToolbar;
    }
    public void setUpToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        if(mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }
}
