package br.com.diogojayme.autobindadapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by diogojayme on 8/26/16.
 */
public interface CustomAdapterMethods{

     RecyclerView.Adapter enableFooter(boolean enabled);

     RecyclerView.Adapter enableHeader(boolean enabled);

     RecyclerView.Adapter bindItems(List items);

     RecyclerView.Adapter bindHeader(Item item);

     RecyclerView.Adapter bindFooter(Item item);

     RecyclerView.Adapter buildItem(int id, Class viewHolderInstance);

     RecyclerView.Adapter buildHeader(int id, Class viewHolderInstance);

     RecyclerView.Adapter buildFooter(int id, Class viewHolderInstance);
}
