package ru.freedomlogic.recycleradapterssample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private SimpleAdapter mSimpleAdapter;
    private HeadersAdapter mHeadersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSimpleAdapter = new SimpleAdapter();
        mSimpleAdapter.addAll("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");

        mHeadersAdapter = new HeadersAdapter();
        mHeadersAdapter.addAll(
                "Header", "Item 1", "Item 2",
                "Header", "Item 3", "Item 4", "Item 5"
        );

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSimpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.simple_list:
                mRecyclerView.setAdapter(mSimpleAdapter);
                return true;

            case R.id.headers_list:
                mRecyclerView.setAdapter(mHeadersAdapter);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}