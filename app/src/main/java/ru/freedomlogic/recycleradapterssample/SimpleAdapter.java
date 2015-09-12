package ru.freedomlogic.recycleradapterssample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.freedomlogic.recycleradapters.ArrayAdapter;

public class SimpleAdapter extends ArrayAdapter<String, SimpleAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
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
