package diogojme.github.com.adapterbuilder.sample;

import android.view.View;

import diogojme.github.com.adapterbuilder.GenericItemViewHolder;
import diogojme.github.com.adapterbuilder.HolderItemClickListener;

/**
 * Created by diogojayme on 1/9/17.
 */

public class FooterItemViewHolder extends GenericItemViewHolder {


    public FooterItemViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(Object item) {

    }

    @Override
    public void addListener(final HolderItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFooterClick(null);
            }
        });
    }
}
