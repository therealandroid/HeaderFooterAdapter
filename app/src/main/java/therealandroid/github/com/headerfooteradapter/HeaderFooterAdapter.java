package therealandroid.github.com.headerfooteradapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by diogojayme on 8/26/16.
 */
public abstract class HeaderFooterAdapter extends RecyclerView.Adapter {

    public static final int HEADER = 1;
    public static final int FOOTER = 2;
    public static final int NORMAL = 3;

    private int totalItems;

    private boolean hasHeader;
    private boolean hasFooter;

    private HashMap<Integer, ViewHolderObject> holders = new HashMap();

    /**
     * Update the total size of the list, so the adapter can calculate
     * footer and header position
     *
     * @param count
     */
    public void updateTotalItems(int count) {
        totalItems = count;
    }

    /**
     * Enable or disable footer
     * <p>
     * default value = false
     *
     * @param enabled true to show footer or false to remove
     */
    public HeaderFooterAdapter enableFooter(boolean enabled) {
        hasFooter = enabled;
        notifyDataSetChanged();
        return this;
    }

    /**
     * Enable or disable header
     * <p>
     * default value = false
     *
     * @param enabled true to show header or false to remove
     */
    public HeaderFooterAdapter enableHeader(boolean enabled) {
        hasHeader = enabled;
        notifyDataSetChanged();
        return this;
    }

    /**
     * @param id             {@link HeaderFooterAdapter#HEADER} Constant value = 1
     *                       {@link HeaderFooterAdapter#FOOTER} Constant value = 2
     *                       {@link HeaderFooterAdapter#NORMAL} Constant value = 3
     * @param layoutResource the resource of the layout you want to inflate
     * @param viewHolder     the class you want to create
     */
    public HeaderFooterAdapter withView(int id, @LayoutRes int layoutResource, Class<? extends RecyclerView.ViewHolder> viewHolder) {
        holders.put(id, new ViewHolderObject(id, layoutResource, viewHolder));
        return this;
    }

    /**
     * Instantiate your custom ViewHolder class
     *
     * @param parent
     * @param viewType
     * @return RecyclerView.ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderObject holder = holders.get(viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(holder.getResourceId(), parent, false);
        Object object = ReflectionUtil.newInstance(holder.getViewHolder(), view);
        return (RecyclerView.ViewHolder) object;
    }

    /**
     * Check if current position is header position
     *
     * @param position
     * @return true if matches header with current rules "position == 0 and hasHeader == true "
     */
    public boolean isHeaderPosition(int position) {
        return hasHeader && position == 0;
    }

    /**
     * Check if current position is footer position
     *
     * @param position
     * @return true if matches footer with current rules "position == totalItems.size() -1 and hasFooter == true"
     */
    public boolean isFooterPosition(int position) {
        int lastPosition = getItemCount() - 1;
        return hasFooter && position == lastPosition;
    }

    @Override
    public int getItemCount() {
        return totalItems + (hasHeader ? 1 : 0) + (hasFooter ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            return HEADER;
        } else if (isFooterPosition(position)) {
            return FOOTER;
        } else {
            return NORMAL;
        }
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public int getRealPosition(int currentPosition){
        return hasHeader ? currentPosition - 1 : currentPosition;
    }
}
