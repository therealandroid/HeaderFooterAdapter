package br.com.diogojayme.autobindadapter.sample;

import android.view.View;

import br.com.diogojayme.autobindadapter.GenericItemViewHolder;
import br.com.diogojayme.autobindadapter.ViewHolderClickListener;


/**
 * Created by diogojayme on 8/28/16.
 */
public class FooterViewHolder extends GenericItemViewHolder<FooterItem> {

    public FooterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(final FooterItem item, final ViewHolderClickListener<FooterItem> listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFooterClick(item);
            }
        });
    }

}
