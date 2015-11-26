package ru.freedomlogic.recycleradapters;

import com.android.internal.util.Predicate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class FilterableAdapter<T, VH extends RecyclerView.ViewHolder>
        extends ArrayAdapter<T, VH>
        implements Filterable {

    private final List<T> mOriginalDataSet;

    private NoSearchResults mNoSearchResults;

    @NonNull
    public abstract Predicate<T> setFilter(@NonNull String filterPattern);

    public FilterableAdapter(@NonNull final List<T> dataSet,
            @NonNull NoSearchResults instance) {
        super();

        addAll(dataSet);

        mOriginalDataSet = new CopyOnWriteArrayList<>(dataSet);
        mNoSearchResults = instance;
    }

    @Override
    public Filter getFilter() {
        //noinspection ReturnOfInnerClass
        return new ContentFilter(this);
    }

    public interface NoSearchResults {

        void showEmptyResultsView();
        void hideEmptyResultsView();
    }

    private final class ContentFilter extends Filter {

        private final FilterableAdapter mAdapter;

        private final List<T> mFilteredList;

        public ContentFilter(final FilterableAdapter adapter) {
            super();
            mAdapter = adapter;
            mFilteredList = new CopyOnWriteArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(final CharSequence constraint) {
            mFilteredList.clear();

            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                mFilteredList.addAll(mOriginalDataSet);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for (final T item : mOriginalDataSet) {
                    if (setFilter(filterPattern).apply(item)) {
                        mFilteredList.add(item);
                    }
                }
            }

            results.values = mFilteredList;
            results.count = mFilteredList.size();

            return results;
        }

        @Override
        protected void publishResults(final CharSequence constraint, final FilterResults results) {
            mAdapter.clear();

            //noinspection unchecked
            mAdapter.addAll((CopyOnWriteArrayList<T>) results.values);
            mAdapter.notifyDataSetChanged();

            if (mAdapter.isEmpty()) {
                mNoSearchResults.showEmptyResultsView();
            } else {
                mNoSearchResults.hideEmptyResultsView();
            }
        }
    }
}
