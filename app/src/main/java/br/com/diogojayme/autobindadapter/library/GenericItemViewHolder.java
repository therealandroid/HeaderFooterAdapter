package br.com.diogojayme.autobindadapter.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by diogojayme on 8/29/16.
 */
public abstract class GenericItemViewHolder<I> extends RecyclerView.ViewHolder{

    public GenericItemViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindItem(I item);
}
