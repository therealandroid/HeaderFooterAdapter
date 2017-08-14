package therealandroid.github.com.headerfooteradapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

public class ViewHolderObject {
    private int id;
    private int resourceId;
    private Class<? extends RecyclerView.ViewHolder> viewHolder;

    public ViewHolderObject(int id, @LayoutRes int resourceId, Class<? extends RecyclerView.ViewHolder> viewHolder) {
        this.id = id;
        this.resourceId = resourceId;
        this.viewHolder = viewHolder;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Class<? extends RecyclerView.ViewHolder> getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(Class<? extends RecyclerView.ViewHolder> viewHolder) {
        this.viewHolder = viewHolder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}