package ru.freedomlogic.recycleradapterssample;

import com.android.internal.util.Predicate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.freedomlogic.recycleradapters.FilterableAdapter;

/**
 * Created by Fi5t on 27/11/15.
 */
public class SimpleFilterableAdapter
        extends FilterableAdapter<String, SimpleFilterableAdapter.ViewHolder> {

    public SimpleFilterableAdapter(@NonNull final List dataSet,
            @NonNull final NoSearchResults instance) {
        super(dataSet, instance);
    }

    @NonNull
    @Override
    public Predicate setFilter(@NonNull final String filterPattern) {
        // Strongly recommended replaced it by a lambda expression
        // Retrolambda: https://github.com/orfjackal/retrolambda
        return new Predicate<String>() {
            @Override
            public boolean apply(final String item) {
                return item.contains(filterPattern);
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {        View
            view;
        ViewHolder viewHolder;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.text.setText(getItem(position));
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text)
        TextView text;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
