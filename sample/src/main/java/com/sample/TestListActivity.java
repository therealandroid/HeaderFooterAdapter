package com.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import therealandroid.github.com.headerfooteradapter.HeaderFooterAdapter;
import therealandroid.github.com.lib.EndlessRecyclerOnScrollListener;


/**
 * Created by diogojayme on 14/08/17.
 */

public class TestListActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCustomAdapter myCustomAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list_activity);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        myCustomAdapter = new MyCustomAdapter<>(loadData());
        myCustomAdapter
                .withView(HeaderFooterAdapter.FOOTER, R.layout.footer_item, FooterViewHolder.class)
                .withView(HeaderFooterAdapter.HEADER, R.layout.header_item, HeaderViewHolder.class)
                .withView(HeaderFooterAdapter.NORMAL, R.layout.normal_item, ItemViewHolder.class);

        myCustomAdapter.enableFooter(true);
        myCustomAdapter.enableHeader(true);

        recyclerView.setAdapter(myCustomAdapter);

        Button addHeader = (Button) findViewById(R.id.add_header);
        Button removeHeader = (Button) findViewById(R.id.remove_header);
        Button addFooter = (Button) findViewById(R.id.add_footer);
        Button removeFooter = (Button) findViewById(R.id.remove_footer);

        addHeader.setOnClickListener(this);
        addFooter.setOnClickListener(this);
        removeFooter.setOnClickListener(this);
        removeHeader.setOnClickListener(this);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData(current_page);
            }
        });
    }

    private List<ListItem> loadData() {
        List<ListItem> items = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            items.add(new ListItem());
        }

        return items;
    }

    public void loadMoreData(int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ListItem> items = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    items.add(new ListItem());
                }

                try {
                    Thread.sleep(2000);
                    myCustomAdapter.appendMoreData(items);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myCustomAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_header:
                myCustomAdapter.enableHeader(true);
                break;
            case R.id.remove_header:
                myCustomAdapter.enableHeader(false);
                break;
            case R.id.add_footer:
                myCustomAdapter.enableFooter(true);
                break;
            case R.id.remove_footer:
                myCustomAdapter.enableFooter(false);
                break;
        }
    }
}
