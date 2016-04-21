package net.dong.gankd.view.fragment;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import net.dong.gankd.model.adapter.PictureRecyclerViewAdapter;
import net.dong.gankd.model.adapter.RecyclerViewAdapter;
import static net.dong.gankd.model.Common.*;


/**
 * Created by dong on 16/4/19.
 */
public class BenefitFragment extends BaseFragment{

    private PictureRecyclerViewAdapter adapter;
    private StaggeredGridLayoutManager manager;



    @Override
    protected RecyclerViewAdapter<PictureRecyclerViewAdapter.PictureRecyclerViewHolder> getRecyclerViewAdapter() {
        adapter = new PictureRecyclerViewAdapter(getActivity());
        return adapter;
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewManager() {
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        return manager;
    }

    @Override
    protected String getCategory() {
        return SOURCE_TYPE_BENEFIT;
    }


}
