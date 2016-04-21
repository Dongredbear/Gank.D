package net.dong.gankd.model.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import net.dong.gankd.R;
import net.dong.gankd.model.data.GankItem;
import net.dong.gankd.view.activity.PictureDetailActivity;
import net.dong.gankd.view.customView.RatioImageView;

import java.util.ArrayList;

/**
 * Created by dong on 16/4/19.
 */
public class PictureRecyclerViewAdapter extends RecyclerViewAdapter<PictureRecyclerViewAdapter.PictureRecyclerViewHolder> {

    public PictureRecyclerViewAdapter(Activity context){
        super(context);
    }


    @Override
    public PictureRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.adapter_picture_recycler_view, parent, false);
        return new PictureRecyclerViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final PictureRecyclerViewHolder holder, int position) {
        holder.textView.setText(items.get(position).getAuthor());
        holder.cardView.setTag(position);

        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = holder.imageView.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
        Picasso.with(context)
                .load(items.get(position).getUrl())
                .transform(transformation)
                .into(holder.imageView);
    }

    public class PictureRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public PictureRecyclerViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            imageView = (ImageView) itemView.findViewById(R.id.card_view_image);
            textView = (TextView) itemView.findViewById(R.id.card_view_text);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PictureDetailActivity.launch(context,imageView,items.get((int)v.getTag()).getUrl());
        }
    }

}
