package net.dong.gankd.model.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static net.dong.gankd.model.Common.*;

import net.dong.gankd.R;
import net.dong.gankd.view.activity.PictureDetailActivity;
import net.dong.gankd.view.activity.WebActivity;

/**
 * Created by dong on 16/4/21.
 */
public class AllRecyclerViewAdapter extends RecyclerViewAdapter<AllRecyclerViewAdapter.ALLRecyclerViewHolder> {

    public AllRecyclerViewAdapter(Activity context) {
        super(context);
    }

    @Override
    public AllRecyclerViewAdapter.ALLRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.adapter_all_recycler_view, parent, false);
        return new AllRecyclerViewAdapter.ALLRecyclerViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final AllRecyclerViewAdapter.ALLRecyclerViewHolder holder, int position) {

        holder.cardView.setTag(position);

        int categoryResId = 0;
        switch (items.get(position).getType()){
            case SOURCE_TYPE_ANDROID:
                categoryResId = R.drawable.android;
                break;
            case SOURCE_TYPE_IOS:
                categoryResId = R.drawable.ios;
                break;
            case SOURCE_TYPE_BENEFIT:
                break;
            case SOURCE_TYPE_EXPAND_VIDEO:
                categoryResId = R.drawable.video;
                break;
            case SOURCE_TYPE_EXPAND_RES:
            case SOURCE_TYPE_EXPAND_APP:
            case SOURCE_TYPE_EXPAND_POST:
                categoryResId = R.drawable.resource;
                break;
            case SOURCE_TYPE_WEB:
                categoryResId = R.drawable.web;
                break;
        }

        if (categoryResId != 0){
            Picasso.with(context)
                    .load(categoryResId)
                    .into(holder.categoryImage);
            holder.contentText.setVisibility(View.VISIBLE);
            holder.categoryImage.setVisibility(View.VISIBLE);
            holder.benefitImage.setVisibility(View.GONE);
            holder.contentText.setText(items.get(position).getDescribe());
        }else {
            holder.contentText.setVisibility(View.GONE);
            holder.categoryImage.setVisibility(View.GONE);
            holder.benefitImage.setVisibility(View.VISIBLE);

            Picasso.with(context)
                    .load(items.get(position).getUrl())
                    .into(holder.benefitImage);
        }

        holder.authorText.setText(items.get(position).getAuthor());
        holder.sourceText.setText(items.get(position).getSource());
    }



    public class ALLRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cardView;
        ImageView categoryImage,benefitImage;
        TextView contentText,authorText,sourceText;


        public ALLRecyclerViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);
            categoryImage = (ImageView) itemView.findViewById(R.id.card_view_category_image);
            benefitImage = (ImageView) itemView.findViewById(R.id.card_view_image);
            contentText = (TextView) itemView.findViewById(R.id.card_view_content_text);
            authorText = (TextView) itemView.findViewById(R.id.card_view_author_text);
            sourceText = (TextView) itemView.findViewById(R.id.card_view_source_text);
        }

        @Override
        public void onClick(View v) {
            if (items.get((int)v.getTag()).getType().equals(SOURCE_TYPE_BENEFIT)){
                PictureDetailActivity.launch(context,benefitImage,items.get((int)v.getTag()).getUrl());
            }else {
                WebActivity.launch(context, cardView, items.get((int)v.getTag()).getUrl());
            }
        }
    }


}
