package net.dong.gankd.model.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dong on 16/4/20.
 */
public class GankRes {

    @SerializedName("error")
    private boolean isDataReceiveError;

    @SerializedName("results")
    private GankItem[] items;

    public boolean isDataReceiveError() {
        return isDataReceiveError;
    }


    public GankItem[] getItems() {
        return items;
    }

    public void setItems(GankItem[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
