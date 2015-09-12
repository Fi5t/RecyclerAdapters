package ru.freedomlogic.recycleradapterssample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.freedomlogic.recycleradapters.HeadersArrayAdapter;

public class HeadersAdapter extends HeadersArrayAdapter<String, RecyclerView.ViewHolder> {

    @Override
    public boolean isHeader(final int position) {
        return "Header".equals(getItem(position));
    }

    @Override
    public int getHeaderLayoutId() {
        return R.layout.list_header;
    }

    @Override
    public RecyclerView.ViewHolder newHeaderViewHolder(@NonNull final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.list_item;
    }

    @Override
    public RecyclerView.ViewHolder newItemViewHolder(@NonNull final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void setHeader(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.header.setText(getItem(position));
    }

    @Override
    public void setItem(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.text.setText(getItem(position));
    }

    public static final class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.header)
        TextView header;

        public HeaderViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text)
        TextView text;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
