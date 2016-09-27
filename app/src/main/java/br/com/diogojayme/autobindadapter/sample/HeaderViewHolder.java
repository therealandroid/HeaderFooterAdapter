package br.com.diogojayme.autobindadapter.sample;

import android.view.View;
import android.widget.TextView;

import br.com.diogojayme.autobindadapter.GenericItemViewHolder;
import br.com.diogojayme.autobindadapter.R;
import br.com.diogojayme.autobindadapter.ViewHolderClickListener;

/**
 * Created by diogojayme on 8/28/16.
 */
public class HeaderViewHolder extends GenericItemViewHolder<HeaderItem> {
    TextView title;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.header_item_title);
    }

    @Override
    public void bindItem(final HeaderItem item, final ViewHolderClickListener<HeaderItem> listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHeaderClick(item);
            }
        });

        title.setText(item.getTitle());
    }


}
