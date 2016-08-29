package br.com.diogojayme.autobindadapter.library;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogojayme on 8/26/16.
 */
public class AutoBindingAdapter<I> extends RecyclerView.Adapter<GenericItemViewHolder<I>> implements CustomAdapterMethods{

    private List<I> items = null;
    private Item headerItem = null;
    private Item footerItem = null;
    private Class normalHolder = null;
    private Class headerHolder = null;
    private Class footerHolder = null;
    private int resourceId = -1;
    private int headerResourceId = -1;
    private int footerResourceId = -1;
    private boolean hasHeader;
    private boolean hasFooter;

    @Override
    public AutoBindingAdapter enableFooter(boolean enabled) {
        hasFooter = enabled;
        return this;
    }

    @Override
    public AutoBindingAdapter enableHeader(boolean enabled) {
        hasHeader = enabled;
        return this;
    }

    public boolean isHeaderShowing(){
        return hasHeader;
    }

    public boolean isFooterShowing(){
        return hasFooter;
    }

    @Override
    public AutoBindingAdapter bindItems(List items) {
        if(this.items == null){
            this.items = new ArrayList<>();
        }

        this.items.addAll(items);
        return this;
    }

    @Override
    public AutoBindingAdapter bindHeader(Item h){
        this.headerItem = h;
        return this;
    }

    @Override
    public AutoBindingAdapter bindFooter(Item f) {
        this.footerItem = f;
        return this;
    }

    @Override
    public AutoBindingAdapter buildItem(int id, Class viewHolderInstance) {
        if(id == -1)
            throw new Resources.NotFoundException("Cannot inflate the resource id " + id);

        this.normalHolder = viewHolderInstance;
        this.resourceId = id;
        return this;
    }

    @Override
    public AutoBindingAdapter buildHeader(int id, Class viewHolderClass) {
        if(id == -1)
            throw new Resources.NotFoundException("Invalid resource item id");

        if(viewHolderClass == null)
            throw new NullPointerException("You must add a FooterViewHolder Class that extends BaseItemViewHolder");

        this.headerResourceId = id;
        this.headerHolder = viewHolderClass;
        return this;
    }

    @Override
    public AutoBindingAdapter buildFooter(int id, Class viewHolderClass) {
        if(id == -1)
            throw new Resources.NotFoundException("Invalid resource item id");

        if(viewHolderClass == null)
            throw new NullPointerException("You must add a FooterViewHolder Class that extends BaseItemViewHolder");

        this.footerResourceId = id;
        this.footerHolder = viewHolderClass;
        return this;
    }

    @Override
    public GenericItemViewHolder<I> onCreateViewHolder(ViewGroup parent, int viewType) {
        GenericItemViewHolder object;

        if(viewType == HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(headerResourceId, parent, false);
            object =  ViewHolderHelper.generateViewHolder(headerHolder, view);
        }else if(viewType == FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(footerResourceId, parent, false);
            object = ViewHolderHelper.generateViewHolder(footerHolder, view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
            object = ViewHolderHelper.generateViewHolder(normalHolder, view);
        }

        return object;
    }

    @Override
    public void onBindViewHolder(GenericItemViewHolder holder, int position) {
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

    public static  final int HEADER = 33;
    public static  final int NORMAL = 44;
    public static  final int FOOTER = 55;


}
