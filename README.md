# Android-AutoBindAdapter
I Was very tired and angry of creating adapters all the time, inflating ViewHolders, adding headers and footers, but before get mad I build this library to help me write less code and save time creating adapters.

`This is a Adapter for RecyclerView and can be used both as List or Grid.`

#I kill myself if I have to create one more adapter ;<

##Usage

   Your ViewHolder class must extends `GenericItemViewHolder`
  
  ```java
  public class FooterViewHolder extends GenericItemViewHolder<FooterItem> {

    public FooterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(FooterItem item) {

    }
 }
 ```
   Header and Footer items must implement the interface `Item`
 
 ```java
   HeaderItem headerItem = new HeaderItem();   //this implements Item              
   FooterItem footerItem = new FooterItem();   //this too...
```

##Calling the adapter

   ```java
   AutoBindingAdapter adapter = new AutoBindingAdapter()
                .buildItem(R.layout.normal_item, ItemViewHolder.class)
                .buildHeader(R.layout.header_item, HeaderViewHolder.class)
                .buildFooter(R.layout.footer_item, FooterViewHolder.class)
                .bindItems(catchPokemons())
                .bindHeader(headerItem)
                .bindFooter(footerItem);

    adapter.enableHeader(true);
    adapter.enableFooter(true);
  ```
  
##Are you using Grid?

   ```java
   GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(adapter.isHeaderPosition(position)){
                        return 2;
                    }else if(adapter.isFooterPosition(position)){
                        return 2;
                    }else{
                        return 1;
                    }
                }
            });
   recyclerView.setLayoutManager(gridLayoutManager);
  ```
