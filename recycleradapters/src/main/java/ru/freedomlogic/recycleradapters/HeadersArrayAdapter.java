package ru.freedomlogic.recycleradapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class HeadersArrayAdapter<T, VH extends RecyclerView.ViewHolder>
        extends ArrayAdapter<T, VH> {

    protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_ITEM = 1;


    public HeadersArrayAdapter() {
    }

    public HeadersArrayAdapter(final boolean hasStableIds) {
        super(hasStableIds);
    }

    public abstract boolean isHeader(final int position);

    public abstract int getHeaderLayoutId();

    public abstract VH newHeaderViewHolder(@NonNull final View view);

    public abstract int getItemLayoutId();

    public abstract VH newItemViewHolder(@NonNull final View view);

    public abstract void setHeader(@NonNull final VH holder, final int position);

    public abstract void setItem(@NonNull final VH holder, final int position);

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
        VH viewHolder;

        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(getHeaderLayoutId(), parent, false);
            viewHolder = newHeaderViewHolder(view);

        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(getItemLayoutId(), parent, false);
            viewHolder = newItemViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        int viewType = getItemViewType(position);

        if (viewType == TYPE_HEADER) {
            setHeader(holder, position);
        } else {
            setItem(holder, position);
        }
    }
}
