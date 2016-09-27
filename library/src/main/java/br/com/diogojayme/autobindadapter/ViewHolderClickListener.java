package br.com.diogojayme.autobindadapter;

/**
 * Created by diogojayme on 9/27/16.
 */

public interface ViewHolderClickListener<I> {
    void onHeaderClick(Item object);
    void onFooterClick(Item object);
    void onItemClick(I object, int position);
}
