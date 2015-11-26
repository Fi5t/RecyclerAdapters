package ru.freedomlogic.recycleradapterssample;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterableActivity extends AppCompatActivity
        implements SimpleFilterableAdapter.NoSearchResults, SearchView.OnQueryTextListener {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.no_search_results)
    TextView mNoSearchResults;

    private SimpleFilterableAdapter mSimpleFilterableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterable);
        ButterKnife.bind(this);

        final List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4",
                "Item 5");

        mSimpleFilterableAdapter = new SimpleFilterableAdapter(items, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSimpleFilterableAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();

        menu.clear();
        inflater.inflate(R.menu.menu_with_search, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showEmptyResultsView() {
        mNoSearchResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyResultsView() {
        mNoSearchResults.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(final String query) {
        return filter(query);
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        return filter(newText);
    }

    private boolean filter(final String newText) {
        if (mSimpleFilterableAdapter != null) {
            mSimpleFilterableAdapter.getFilter().filter(newText);
            return true;
        } else {
            return false;
        }
    }
}
