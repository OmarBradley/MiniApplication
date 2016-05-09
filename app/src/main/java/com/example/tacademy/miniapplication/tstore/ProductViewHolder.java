package com.example.tacademy.miniapplication.tstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.function.BiConsumer;
import com.bumptech.glide.Glide;
import com.example.tacademy.miniapplication.R;
import com.example.tacademy.miniapplication.data.category.product.Product;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by Tacademy on 2016-05-09.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.image_thumb) ImageView thumbView;
    @Bind(R.id.text_name) TextView nameView;
    @Bind(R.id.text_description) TextView descriptionView;
    @Bind(R.id.text_score) TextView scoreView;
    @Bind(R.id.text_download) TextView downloadView;
    @Bind(R.id.text_detail) TextView detailView;

    Product product;
    @Setter @NonNull
    BiConsumer<View, Product> onItemClickListener;
    private Context context;


    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(view -> {
            onItemClickListener.accept(view, product);
        });
    }

    public void setProduct(Product product) {
        this.product = product;
        setThumbnailIImage(product.getThumbnailUrl());
        nameView.setText(product.getName());
        descriptionView.setText(product.getDescription());
        scoreView.setText(""+product.getScore());
        downloadView.setText(""+product.getDownloadCount());
        detailView.setText(product.getDetailDescription());
    }

    private void setThumbnailIImage(String thumbnailUrl) {
        if (!TextUtils.isEmpty(thumbnailUrl)) {
            Glide.with(context).load(thumbnailUrl).into(thumbView);
        } else {
            thumbView.setImageResource(R.mipmap.ic_launcher);
        }
    }

}
