package com.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import therealandroid.github.com.headerfooteradapter.HeaderFooterAdapter;


/**
 * Created by diogojayme on 14/08/17.
 */

public class MyCustomAdapter<T> extends HeaderFooterAdapter {
    private List<T> items;

    public MyCustomAdapter(List<T> items) {
        this.items = items;
        updateTotalItems(this.items.size());
    }

    public void appendMoreData(List<T> items){
        this.items.addAll(items);
        updateTotalItems(this.items.size());
    }

    public void removeData(int position){
        this.items.remove(position);
        updateTotalItems(this.items.size());
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (isHeaderPosition(position)) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "WOWWWWW you did a click in the title ", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (isFooterPosition(position)) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Awwwesome footer", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ListItem normalItem = (ListItem) items.get(getRealPosition(position));
            ItemViewHolder normalViewHolder = (ItemViewHolder) holder;
            normalViewHolder.textView.setText("This is an item " + getRealPosition(position));

            normalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "nah, normal click - " + getRealPosition(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
