# Android-AutoBindAdapter v1.0
I Was very tired and angry of creating adapters all the time, inflating ViewHolders, adding headers and footers, but before get mad I build this library to help me write less code and save time creating adapters.

`This is a Adapter for RecyclerView and can be used both as List or Grid.`

#I kill myself if I have to create one more adapter ;<

##Usage

   Your ViewHolder class must extends `GenericItemViewHolder`
  
  ```java
  public class FooterViewHolder extends GenericItemViewHolder<Pokemon> {

    public FooterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(Pokemon item) {
         //bind your view
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
                .buildHeader(R.layout.header_item, HeaderViewHolder.class)       //optional
                .buildFooter(R.layout.footer_item, FooterViewHolder.class)       //optional
                .bindItems(catchPokemons())
                .bindHeader(headerItem)                                          //optional
                .bindFooter(footerItem);                                         //optional

    adapter.enableHeader(true);                                                  //optional
    adapter.enableFooter(true);                                                  //optional
  ```
  
##Do you want to paginate?

I added an `EndlessScrollListener` if you want to implement pagination

   For Grid

   ```java
      adapter.asGrid(recyclerView, numColumns, AutoBindAdapter.OnLoadMoreListener);
  ```
  
   For List
   
   ```java
      adapter.asList(recyclerView, AutoBindAdapter.OnLoadMoreListener);
  ```
