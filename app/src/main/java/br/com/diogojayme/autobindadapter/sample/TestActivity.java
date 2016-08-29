package br.com.diogojayme.autobindadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.diogojayme.autobindadapter.R;
import br.com.diogojayme.autobindadapter.library.AutoBindingAdapter;
import br.com.diogojayme.autobindadapter.library.EndlessRecyclerOnScrollListener;

/**
 * Created by diogojayme on 8/28/16.
 */
public class TestActivity extends AppCompatActivity {

    boolean asGridLayout = true;
    AutoBindingAdapter adapter;
    boolean isLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.features_fragment_recycler_view);

        HeaderItem headerItem = new HeaderItem("My Pokedex");   //just a normal header with static data
        FooterItem footerItem = new FooterItem();               //just a normal footer with loading

         adapter = new AutoBindingAdapter()
                .buildItem(R.layout.normal_item, ItemViewHolder.class)
                .buildHeader(R.layout.header_item, HeaderViewHolder.class)
                .buildFooter(R.layout.footer_item, FooterViewHolder.class)
                .bindItems(catchPokemons())
                .bindHeader(headerItem)
                .bindFooter(footerItem);

        adapter.enableHeader(true);

        setLayoutManager(adapter, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(final AutoBindingAdapter adapter, RecyclerView recyclerView){
        if(asGridLayout){
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

            recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
                @Override
                public void onLoadMore(int current_page) {
                    if(!isLoading) {
                        loadMore();
                    }
                }
            });
        }else{
            LinearLayoutManager linearLayout = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayout);
            recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayout) {
                @Override
                public void onLoadMore(int current_page) {
                    if(!isLoading) {
                        loadMore();
                    }
                }
            });
        }
    }

    public void loadMore(){
        isLoading = true;
        adapter.enableFooter(true);
        adapter.notifyDataSetChanged();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final List<Pokemon> pokemons = catchMorePokemons();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.enableFooter(false);
                        adapter.bindItems(pokemons);
                        isLoading = false;
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public List<Pokemon> catchPokemons(){
        List<Pokemon> pokemons = new ArrayList<>();

        pokemons.add(new Pokemon("Abra", R.drawable.abra));
        pokemons.add(new Pokemon("Alakazan", R.drawable.alakaza));
        pokemons.add(new Pokemon("Blastoise", R.drawable.blasto));
        pokemons.add(new Pokemon("Bubassaur", R.drawable.buba));
        pokemons.add(new Pokemon("Charmander", R.drawable.charm));
        pokemons.add(new Pokemon("Ekans", R.drawable.ekans));
        pokemons.add(new Pokemon("Kadabra", R.drawable.kadabra));
        pokemons.add(new Pokemon("Kilbone", R.drawable.kilbone));
        pokemons.add(new Pokemon("Onix", R.drawable.onix));
        pokemons.add(new Pokemon("Pidgey", R.drawable.pidgey));
        pokemons.add(new Pokemon("Pikachu", R.drawable.pikachu));
        pokemons.add(new Pokemon("Squirtle", R.drawable.squirt));
        pokemons.add(new Pokemon("Vulpix", R.drawable.vulpix));

        return pokemons;
    }

    public List<Pokemon> catchMorePokemons(){
        List<Pokemon> pokemons = new ArrayList<>();

        pokemons.add(new Pokemon("Bellsprout", R.drawable.bellsprout));
        pokemons.add(new Pokemon("Diglett", R.drawable.diglett));
        pokemons.add(new Pokemon("Graveler", R.drawable.graveler));
        pokemons.add(new Pokemon("Gyarados", R.drawable.gyarados));
        pokemons.add(new Pokemon("Magmar", R.drawable.magamar));
        pokemons.add(new Pokemon("Muk", R.drawable.muk));
        pokemons.add(new Pokemon("Ponita", R.drawable.ponita));
        pokemons.add(new Pokemon("Tangela", R.drawable.tangela));

        return pokemons;
    }

}
