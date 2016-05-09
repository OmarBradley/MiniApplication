package com.example.tacademy.miniapplication.tstore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.function.BiConsumer;
import com.example.tacademy.miniapplication.R;
import com.example.tacademy.miniapplication.data.category.product.Product;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

/**
 * Created by Tacademy on 2016-05-09.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    List<Product> items = new ArrayList<>();


    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public void add(Product product) {
        items.add(product);
        notifyDataSetChanged();
    }

    public void addAll(List<Product> items) {
        items.addAll(items);
        notifyDataSetChanged();
    }

    @Setter
    BiConsumer<View, Product> onItemClickListener;

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tstore_product, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.setProduct(items.get(position));
        holder.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
