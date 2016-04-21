package net.dong.gankd.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import net.dong.gankd.R;
import net.dong.gankd.model.adapter.PictureRecyclerViewAdapter;
import net.dong.gankd.model.adapter.RecyclerViewAdapter;
import net.dong.gankd.model.data.GankRes;
import net.dong.gankd.model.http.HttpClint;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by dong on 16/4/19.
 */
public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.swipe_refresh_view)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private Gson gson;

    int[] position = new int[2];




    protected int pageN = 0;
    protected final int NUM = 10;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_benefit,container,false);
        ButterKnife.bind(this,view);
        gson = new Gson();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        manager = getRecyclerViewManager();
        recyclerView.setLayoutManager(manager);
        adapter = getRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(getOnBottomListener(manager));
        onRefresh();

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        if (pageN == 0){
            loadMore();
        }else {
            HttpClint.getInstance().getRes(getCategory(),NUM,1,reFreshHttpResponseHandler);
        }

    }

    protected void loadMore(){
        pageN++;
        swipeRefreshLayout.setRefreshing(true);
        HttpClint.getInstance().getRes(getCategory(),NUM,pageN,loadMoreHttpResponseHandler);
    }




    private AsyncHttpResponseHandler loadMoreHttpResponseHandler = new TextHttpResponseHandler() {
        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            //swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            swipeRefreshLayout.setRefreshing(false);
            GankRes res = gson.fromJson(responseString,GankRes.class);
            adapter.addItems(res.getItems());

        }
    };
    private AsyncHttpResponseHandler reFreshHttpResponseHandler = new TextHttpResponseHandler() {
        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            //swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            swipeRefreshLayout.setRefreshing(false);
            GankRes res = gson.fromJson(responseString,GankRes.class);
            adapter.refreshItems(res.getItems());

        }
    };



    protected abstract RecyclerViewAdapter getRecyclerViewAdapter();
    protected abstract RecyclerView.LayoutManager getRecyclerViewManager();
    protected abstract String getCategory();




    RecyclerView.OnScrollListener getOnBottomListener(final RecyclerView.LayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView rv, int dx, int dy) {
                int maxItem = getLastVisiblePosition(layoutManager);
                boolean isBottom = (maxItem >= (adapter.getItemCount() - 1));

                if (!swipeRefreshLayout.isRefreshing() && isBottom) {
                    Toast.makeText(getContext(),"OnScrollListener bottom",Toast.LENGTH_SHORT).show();
                    Log.e(">>>>","bottom");
                    loadMore();
                }
            }
        };
    }


    private int getFirstVisiblePosition(RecyclerView.LayoutManager layoutManager) {
        int position;
        if (layoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            int[] firstPositions = manager.findFirstVisibleItemPositions(new int[manager.getSpanCount()]);
            position = getMinPositions(firstPositions);
        } else {
            position = 0;
        }
        return position;
    }

    private int getLastVisiblePosition(RecyclerView.LayoutManager layoutManager) {
        int position;
        if (layoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            int[] lastPositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = layoutManager.getItemCount() - 1;
        }
        return position;
    }

    private int getMinPositions(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minPosition = Math.min(minPosition, positions[i]);
        }
        return minPosition;
    }

    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

}
