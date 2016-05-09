package com.example.tacademy.miniapplication.tstore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.function.BiConsumer;
import com.example.tacademy.miniapplication.data.category.Category;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

/**
 * Created by Tacademy on 2016-05-09.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    List<Category> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(Category category) {
        items.add(category);
        notifyDataSetChanged();
    }

    public void addAll(List<Category> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Setter
    BiConsumer<View, Category> onItemClickListener;

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.setCategory(items.get(position));
        holder.setItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
