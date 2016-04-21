package net.dong.gankd.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import net.dong.gankd.model.adapter.AllRecyclerViewAdapter;
import net.dong.gankd.model.adapter.PictureRecyclerViewAdapter;
import net.dong.gankd.model.adapter.RecyclerViewAdapter;

import static net.dong.gankd.model.Common.SOURCE_TYPE_ALL;
import static net.dong.gankd.model.Common.SOURCE_TYPE_ANDROID;
import static net.dong.gankd.model.Common.SOURCE_TYPE_BENEFIT;

/**
 * Created by dong on 16/4/19.
 */
public class AllFragment extends BaseFragment{

    private AllRecyclerViewAdapter adapter;
    private LinearLayoutManager manager;
    private String category = SOURCE_TYPE_ALL;



    @Override
    protected RecyclerViewAdapter<AllRecyclerViewAdapter.ALLRecyclerViewHolder> getRecyclerViewAdapter() {
        adapter = new AllRecyclerViewAdapter(getActivity());
        return adapter;
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewManager() {
        manager = new LinearLayoutManager(getContext());
        return manager;
    }

    @Override
    protected String getCategory() {
        return category;
    }

    public AllFragment setCategory(String category) {
        this.category = category;
        return this;
    }


}
