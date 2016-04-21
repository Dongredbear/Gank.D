package net.dong.gankd.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import net.dong.gankd.R;
import net.dong.gankd.model.Common;
import net.dong.gankd.model.adapter.PictureRecyclerViewAdapter;
import net.dong.gankd.model.adapter.TabLayoutFragmentAdapter;
import net.dong.gankd.view.fragment.AllFragment;
import net.dong.gankd.view.fragment.BenefitFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import static net.dong.gankd.model.Common.*;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_cativity_drawerLayout)
    DrawerLayout drawerLayout;

    @Bind(R.id.main_activity_tool_bar)
    Toolbar toolbar;

    @Bind(R.id.main_activity_tab_layout)
    TabLayout categoryView;

    @Bind(R.id.main_activity_view_pager)
    ViewPager viewPager;

    TabLayoutFragmentAdapter tabLayoutFragmentAdapter;

    ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bindView();
    }


    /**
     * bind view
     */
    private void bindView(){
        //tool bar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        fragments = new ArrayList<>();

        fragments.add(new AllFragment());
        fragments.add(new BenefitFragment());
        fragments.add(new AllFragment().setCategory(SOURCE_TYPE_ANDROID));
        fragments.add(new AllFragment().setCategory(SOURCE_TYPE_IOS));
        fragments.add(new AllFragment().setCategory(SOURCE_TYPE_EXPAND_VIDEO));
        fragments.add(new AllFragment().setCategory(SOURCE_TYPE_EXPAND_RES));
        fragments.add(new AllFragment().setCategory(SOURCE_TYPE_WEB));

        tabLayoutFragmentAdapter = new TabLayoutFragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(Common.CATEGORY_NAME));
        categoryView.setTabsFromPagerAdapter(tabLayoutFragmentAdapter);

        viewPager.setAdapter(tabLayoutFragmentAdapter);
        categoryView.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_main, menu);
        return true;
    }

}
