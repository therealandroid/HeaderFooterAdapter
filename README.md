# HeaderFooterAdapter 

[![GitHub version](https://badge.fury.io/gh/therealandroid%2FHeaderFooterAdapter.svg)](https://badge.fury.io/gh/therealandroid%2FHeaderFooterAdapter)
[![JitPack version](https://jitpack.io/v/therealandroid/HeaderFooterAdapter.svg)](https://jitpack.io/#therealandroid/HeaderFooterAdapter)

### Version v1.2 
- <b> (New) </b>Less code to create your header and footer Adapter
- Create your custom header and footer views
- Easy to add or remove header and footer
- <b> (New) </b> Now you can add your custom load more listener 
- Item (Header, footer or normal) clicks can be added by you from your Adapter or ViewHolder 

### Version v1.1 
- Easy to create an Adapter for RecyclerView
- Create your custom header and footer views
- Easy to add or remove header and footer
- Load more listener

### Usage (Deprecated)

   Your FooterViewHolder or HeaderViewHolder class can extends `GenericItemViewHolder` //optional

   ```java
  public class FooterViewHolder extends GenericItemViewHolder<YourObject> {

    public FooterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(YourObject item) {
         //bind your view
    }

    //new
    @Override
    public void addListener(final HolderItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(null, getLayoutPosition());
            }
        });
    }
 }
 ```
 
 #### Builder class
 
  ```java
  AdapterBuilder.Builder builder = new AdapterBuilder.Builder()
             .normalItemResource(R.layout.item)
                .withHolder(NormalItemViewHolder.class)
             .headerItemResource(R.layout.item_header)         //optional
                .withHolder(HeaderItemViewHolder.class)
             .footerItemResource(R.layout.item_footer)         //optional
                .withHolder(FooterItemViewHolder.class)
             .setOnLoadMoreListener(this)                       //optional
             .attachRecyclerView(recyclerView);

  AdapterBuilder adapterBuilder = builder.build();
 ```
   
#### Adding separated data to the adapter
```java
     adapterBuilder.bindHeader(new Object());
     adapterBuilder.bindFooter(new Object());
     adapterBuilder.bindData(new Arraylist());
```

#### Handle Click Listeners

```java
     adapterBuilder.setOnViewHolderItemClickListener(new HolderItemClickListener() {
         @Override
         public void onHeaderClick(Object object) {
             Toast.makeText(SampleActivity.this, "Header Clicked", Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onFooterClick(Object object) {
             Toast.makeText(SampleActivity.this, "Footer Clicked", Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onItemClick(Object object, int position) {
             Toast.makeText(SampleActivity.this, "Item "+position+" Clicked", Toast.LENGTH_SHORT).show();
         }
     });
```
        
#### Enable and disable Header and footer
      
 ```java
   adapterBuilder.enableHeader(true);
   adapterBuilder.enableFooter(true);
 ```
 
#### Span Size Lookup for Grid

 ```java
    adapterBuilder.setSpanSizeLookup(1, 2); // For Grid
 ``` 
  
#### Attach adapter to recyclerView

 ```java
    recyclerView.setAdapter(adapterBuilder);
 ``` 
 #### Project Integration

 ```  
 allprojects {
    repositories {
      maven { url 'https://jitpack.io' }
    }
 }
	
compile 'com.github.therealandroid:HeaderFooterAdapter:1.2.0'

 
