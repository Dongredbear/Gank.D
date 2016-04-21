package net.dong.gankd.model.http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by dong on 16/4/20.
 */
public class HttpClint {

    private static HttpClint instance = new HttpClint();
    private AsyncHttpClient client;

    private HttpClint(){
        client = new AsyncHttpClient();

    }

    public static HttpClint getInstance(){
        return instance;
    }

    public void getRes(String category,int num,int pageN,AsyncHttpResponseHandler handler){
        String url = "http://gank.io/api/data/"+category+"/"+num+"/"+pageN;
        Log.e("URL",url);
        client.get(url,handler);
    }
}
