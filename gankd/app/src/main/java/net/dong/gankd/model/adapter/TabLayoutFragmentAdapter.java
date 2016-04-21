package net.dong.gankd.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import net.dong.gankd.model.Common;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dong on 16/4/19.
 */
public class TabLayoutFragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> category;
    private List<Fragment> fragments;


    public TabLayoutFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> category) {
        super(fm);
        this.fragments = fragments;
        this.category = category;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return category.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
