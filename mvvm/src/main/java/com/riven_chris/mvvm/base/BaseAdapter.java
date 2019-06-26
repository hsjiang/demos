package com.riven_chris.mvvm.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    private List<T> mItems = new ArrayList<>();

    public BaseAdapter(Context context, List<T> items) {
        super();
        mContext = context;
        if (items != null)
            getItems().addAll(items);
    }

    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    protected abstract void onBindViewHolder(@NonNull VH holder, int position, T item);

    public List<T> getItems() {
        return mItems;
    }

    public T getItem(int index) {
        if (index < getItems().size() && index > 0)
            return getItems().get(index);
        return null;
    }

    public void add(T item) {
        getItems().add(item);
        notifyItemInserted(getItems().size() - 1);
    }

    public void add(int index, T item) {
        if (index > getItems().size() || index < 0)
            return;
        getItems().add(index, item);
        notifyItemInserted(index);
    }

    public void addAll(List<T> items) {
        if (items == null)
            return;
        getItems().addAll(items);
        notifyItemRangeInserted(getItems().size(), items.size());
    }

    public void addAll(int index, List<T> items) {
        if (items == null || index > getItems().size() || index < 0)
            return;
        getItems().addAll(index, items);
        notifyItemRangeInserted(index, items.size());
    }

    public void replace(T oldItem, T newItem) {
        int index = getItems().indexOf(oldItem);
        replace(index, newItem);
    }

    public void replace(int index, T item) {
        if (index >= getItems().size() || index < 0)
            return;
        mItems.set(index, item);
        notifyItemChanged(index);
    }

    public void replaceAll(List<T> items) {
        removeAll();
        addAll(items);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        if (index >= getItems().size() || index < 0)
            return;
        getItems().remove(index);
        notifyItemRemoved(index);
    }

    public void remove(T item) {
        remove(getItems().indexOf(item));
    }

    public void removeAll(List<T> items) {
        boolean removed = getItems().removeAll(items);
        if (removed) {
            notifyDataSetChanged();
        }
    }

    public void removeAll() {
        getItems().clear();
        notifyDataSetChanged();
    }

    public boolean contains(T item) {
        return getItems().contains(item);
    }
}
