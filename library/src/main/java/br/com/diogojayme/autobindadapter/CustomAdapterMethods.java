package br.com.diogojayme.autobindadapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by diogojayme on 8/26/16.
 */
public interface CustomAdapterMethods<T>{

     RecyclerView.Adapter enableFooter(boolean enabled);

     RecyclerView.Adapter enableHeader(boolean enabled);

     RecyclerView.Adapter bindItems(List items);

     RecyclerView.Adapter bindHeader(Item item);

     RecyclerView.Adapter bindFooter(Item item);

     RecyclerView.Adapter createItemView(int id, Class viewHolderInstance);

     RecyclerView.Adapter createHeaderView(int id, Class viewHolderInstance);

     RecyclerView.Adapter createFooterView(int id, Class viewHolderInstance);

     List<T> getItems();
}
