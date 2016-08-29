package br.com.diogojayme.autobindadapter.sample;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import br.com.diogojayme.autobindadapter.R;
import br.com.diogojayme.autobindadapter.library.GenericItemViewHolder;

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
    public void bindItem(@Nullable HeaderItem item) {
        title.setText(item.getTitle());
    }


}
