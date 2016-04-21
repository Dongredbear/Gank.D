package net.dong.gankd.model.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import net.dong.gankd.model.data.GankItem;

import java.util.ArrayList;

/**
 * Created by dong on 16/4/20.
 */
public abstract class RecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    protected Activity context;
    protected ArrayList<GankItem> items;

    public RecyclerViewAdapter(Activity context){
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(GankItem[] res) {
        //加载历史
        for (GankItem item : res){
            items.add(item);
        }
        notifyDataSetChanged();
    }

    public void refreshItems(GankItem[] res) {
        //刷新最新
        if (items.size() == 0){
            addItems(res);
        }else {
            for (int i = 0;i<res.length;i++){
                if (res[res.length-1-i].getCreateDate().compareTo(items.get(0).getCreateDate()) > 0){
                    items.add(0,res[res.length-1-i]);
                }
            }
        }
        notifyDataSetChanged();
    }




}
