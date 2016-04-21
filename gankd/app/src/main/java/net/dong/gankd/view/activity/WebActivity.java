package net.dong.gankd.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import net.dong.gankd.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dong on 16/4/21.
 */
public class WebActivity extends BaseActivity {

    public static final String EXTRA_URL = "webactivity_url";

    @Bind(R.id.activity_web_progress)
    ProgressBar progressBar;

    @Bind(R.id.activity_web_view)
    WebView webView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(webView,EXTRA_URL);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new ChromeClient());
        webView.setWebViewClient(new LoveClient());
        Log.e(">>>>>",getIntent().getStringExtra(EXTRA_URL));
        webView.loadUrl(getIntent().getStringExtra(EXTRA_URL));

    }


    private class ChromeClient extends WebChromeClient {

        @Override public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
            }
        }


        @Override public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //setTitle(title);
        }
    }



    private class LoveClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }




    public static void launch(Activity activity, View transitionView, String url) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_URL);
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(EXTRA_URL, url);
        Log.e(">>>>>11",url);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
