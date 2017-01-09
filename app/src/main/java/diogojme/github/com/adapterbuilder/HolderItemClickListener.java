package diogojme.github.com.adapterbuilder;

/**
 * Created by diogojayme on 9/27/16.
 */

public interface HolderItemClickListener<I> {
    void onHeaderClick(I object);
    void onFooterClick(I object);
    void onItemClick(I object, int position);
}
