package net.dong.gankd.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.squareup.picasso.Picasso;

import net.dong.gankd.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dong on 16/4/20.
 */
public class PictureDetailActivity extends BaseActivity {

    public static final String EXTRA_IMAGE = "picturedetailactivity_image";

    @Bind(R.id.activity_picture_image)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(imageView,EXTRA_IMAGE);


        Picasso.with(this)
                .load(getIntent().getStringExtra(EXTRA_IMAGE))
                .into(imageView);


    }

    public static void launch(Activity activity, View transitionView, String url) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, PictureDetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
