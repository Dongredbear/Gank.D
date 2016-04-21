package net.dong.gankd.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;

/**
 * Created by dong on 16/4/20.
 */
public abstract class ToolBarActivity extends BaseActivity {

    protected AppBarLayout mAppBar;
    protected Toolbar mToolbar;

    protected abstract int getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());


    }
}
