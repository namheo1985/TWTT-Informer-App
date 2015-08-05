package org.twtt.twttinformer;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.neo.framework.activities.BaseActivity;
import com.neo.framework.activities.ToolbarActivity;
import com.neo.framework.helpers.UIHelper;

public class MainActivity extends ToolbarActivity {
    public int getIdMainLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onLazyBuildingUI(View root) {
    }
    public void initiateUIFirst(final View root) {
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        setUpToolbar(toolbar);
        super.initiateUIFirst(root);
    }

    @Override
    public void recycle() {
        UIHelper.removeAllViewsOf(getMainView());
    }

    @Override
    public void onFirstResume() {
    }
}
