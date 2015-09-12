package ru.freedomlogic.recycleradapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ArrayAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private final List<T> mList = new CopyOnWriteArrayList<>();

    public ArrayAdapter() {
        setHasStableIds(true);
    }

    public ArrayAdapter(final boolean hasStableIds) {
        setHasStableIds(hasStableIds);
    }


    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public final int getItemCount() {
        return mList.size();
    }

    public final void add(@NonNull final T item) {
        mList.add(item);
        notifyItemInserted(getItemCount());
    }

    public final void add(final int index, @NonNull final T item) {
        mList.add(index, item);
        notifyItemInserted(index);
    }

    public final void addAll(@NonNull Collection<? extends T> collection) {
        if (collection != null) {
            mList.addAll(collection);
            notifyItemRangeInserted(getItemCount(), collection.size());
        }
    }

    public final void addAll(@NonNull T... items) {
        final List<T> list = Arrays.asList(items);

        addAll(list);
        notifyItemRangeInserted(getItemCount(), list.size());
    }

    public final void clear() {
        final int count = getItemCount();

        if (count > 0) {
            for (int i = 0; i < count; ++i) {
                mList.remove(0);
            }

            notifyItemRangeRemoved(0, count);
        }
    }

    public final void remove(@NonNull final T item) {
        mList.remove(item);
        notifyItemRemovedSafe(mList.indexOf(item));
    }

    public final void remove(final int position) {
        if (position < getItemCount()) {
            mList.remove(position);
            notifyItemRemovedSafe(position);
        }
    }

    public final T getItem(final int position) {
        return mList.get(position);
    }

    public final List<T> getList() {
        return mList;
    }


    /**
     * Workaround to avoid a recyclerview bug
     * https://code.google.com/p/android/issues/detail?id=77846
     * https://code.google.com/p/android/issues/detail?id=77232
     */
    private final void notifyItemRemovedSafe(int position) {
        if (position == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRemoved(position);
        }
    }
}
