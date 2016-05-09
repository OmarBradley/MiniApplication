package com.example.tacademy.miniapplication.tstore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.annimon.stream.function.BiConsumer;
import com.example.tacademy.miniapplication.data.category.Category;

import lombok.Setter;

/**
 * Created by Tacademy on 2016-05-09.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {

    TextView nameView;
    Category category;

    @Setter
    @NonNull
    BiConsumer<View, Category> itemClickListener;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        nameView = (TextView) itemView;
        itemView.setOnClickListener(view -> {
            itemClickListener.accept(view, category);
        });
    }

    public void setCategory(Category category) {
        this.category = category;
        nameView.setText(category.getCategoryName());
    }

}
