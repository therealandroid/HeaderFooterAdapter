package diogojme.github.com.adapterbuilder;

import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogojayme on 8/26/16.
 */
public class AdapterBuilder<I> extends RecyclerView.Adapter<GenericItemViewHolder<I>> {
    public static  final int HEADER = 33;
    public static  final int NORMAL = 44;
    public static  final int FOOTER = 55;

    private HolderItemClickListener listener;

    private List<I> items = null;
    private Object headerItem = null;
    private Object footerItem = null;
    private boolean hasHeader;
    private boolean hasFooter;

    private Builder builder;

    public AdapterBuilder(Builder builder){
        this.builder = builder;
    }

    public AdapterBuilder enableFooter(boolean enabled) {
        hasFooter = enabled;
        notifyDataSetChanged();
        return this;
    }

    public AdapterBuilder enableHeader(boolean enabled) {
        hasHeader = enabled;
        notifyDataSetChanged();
        return this;
    }

    public boolean isHeaderShowing(){
        return hasHeader;
    }

    public boolean isFooterShowing(){
        return hasFooter;
    }

    public AdapterBuilder bindData(List items) {
        if(this.items == null){
            this.items = new ArrayList<>();
        }

        this.items.addAll(items);
        return this;
    }

    public AdapterBuilder bindHeader(Object i) {
        this.headerItem = i;
        return this;
    }

    public AdapterBuilder bindFooter(Object i) {
        this.footerItem = i;
        return this;
    }


    public AdapterBuilder setOnViewHolderItemClickListener(HolderItemClickListener listener){
        this.listener = listener;
        return this;
    }

    public List<I> getItems() {
        return items;
    }

    public AdapterBuilder setSpanSizeLookup(final int headerSpanSizeLookup, final int footerSpanSizeLookup) {
        if (builder.validateRecyclerView() && builder.getRecyclerView().getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) builder.getRecyclerView().getLayoutManager();
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isHeaderPosition(position)){
                        return headerSpanSizeLookup;
                    }else if(isFooterPosition(position)) {
                        return footerSpanSizeLookup;
                    } else {
                        return 1;
                    }
                }
            });
        }

        return this;
    }

    @Override
    public GenericItemViewHolder<I> onCreateViewHolder(ViewGroup parent, int viewType) {
        Object object;

        if(viewType == HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(builder.headerResourceId, parent, false);
            object =  ViewHolderHelper.generateViewHolder(builder.headerHolder, view);
        }else if(viewType == FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(builder.footerResourceId, parent, false);
            object = ViewHolderHelper.generateViewHolder(builder.footerHolder, view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(builder.resourceId, parent, false);
            object = ViewHolderHelper.generateViewHolder(builder.normalHolder, view);
        }

        return (GenericItemViewHolder) object;
    }

    @Override
    public void onBindViewHolder(GenericItemViewHolder holder, int position) {
        if(listener != null) {
            holder.addListener(listener);
        }

        if (isHeaderPosition(position)) {
            holder.bindItem(headerItem);
        } else if (isFooterPosition(position)) {
            holder.bindItem(footerItem);
        } else {
            holder.bindItem(getObject(position));
        }
    }

    public I getObject(int position){
        return items.get(position - (hasHeader ? 1 : 0));
    }

    public boolean isHeaderPosition(int position){
        return hasHeader && position == 0;
    }

    public boolean isFooterPosition(int position){
        int lastPosition = getItemCount() - 1;
        return hasFooter && position == lastPosition;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size() + (hasHeader ? 1 : 0) + (hasFooter ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderPosition(position)){
            return HEADER;
        }else if(isFooterPosition(position)){
            return FOOTER;
        }else{
            return NORMAL;
        }
    }

    public static class Builder {

        private int itemTurn = AdapterBuilder.NORMAL;

        private Class normalHolder = null;
        private Class headerHolder = null;
        private Class footerHolder = null;
        private int resourceId = -1;
        private int headerResourceId = -1;
        private int footerResourceId = -1;

        private RecyclerView recyclerView = null;
        private OnLoadMoreListener loadMoreListener;

        public Builder withHolder(Class clazz) {
            switch (itemTurn){
                case HEADER:
                    this.headerHolder = clazz;
                    break;
                case FOOTER:
                    this.footerHolder = clazz;
                    break;
                case NORMAL:
                    this.normalHolder = clazz;
                    break;
            }

            return this;
        }

        public Builder normalItemResource(int id) {
            validateResource(id);
            itemTurn = NORMAL;
            this.resourceId = id;
            return this;
        }

        public Builder headerItemResource(int id) {
            validateResource(id);
            itemTurn = HEADER;
            this.headerResourceId = id;
            return this;
        }

        public Builder footerItemResource(int id) {
            validateResource(id);
            itemTurn = FOOTER;
            this.footerResourceId = id;
            return this;
        }

        private void validateResource(int id){
            if(id == -1)
                throw new Resources.NotFoundException("Invalid resource item id");
        }

        public Builder attachRecyclerView(RecyclerView recyclerView){
            this.recyclerView = recyclerView;
            return this;
        }

        public Builder setOnLoadMoreListener(final OnLoadMoreListener loadMoreListener) {
            this.loadMoreListener = loadMoreListener;
            return this;
        }

        public RecyclerView getRecyclerView(){
            return recyclerView;
        }

        public AdapterBuilder build(){
            if (validateRecyclerView()) {
                if (recyclerView.getLayoutManager() == null) {
                    Log.e("AdapterBuilder", "LayoutManager was not attached to RecyclerView");
                } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
                        @Override
                        public void onLoadMore(int current_page) {
                            loadMoreListener.onLoadMore(current_page);
                        }
                    });
                } else {
                    GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
                        @Override
                        public void onLoadMore(int current_page) {
                            loadMoreListener.onLoadMore(current_page);
                        }
                    });
                }
            }

            return new AdapterBuilder(this);
        }

        private boolean validateRecyclerView(){
            if(recyclerView == null) {
                Log.e("AdapterBuilder", "RecyclerView was not attached to Adapter, call attachRecuclerView before");
                return false;
            }else {
                return true;
            }
        }
    }

}
