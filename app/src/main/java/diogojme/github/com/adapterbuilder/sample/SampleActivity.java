package diogojme.github.com.adapterbuilder.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import diogojme.github.com.adapterbuilder.HolderItemClickListener;
import diogojme.github.com.adapterbuilder.AdapterBuilder;
import diogojme.github.com.adapterbuilder.OnLoadMoreListener;
import diogojme.github.com.adapterbuilder.R;

/**
 * Created by diogojayme on 1/9/17.
 */

public class SampleActivity extends AppCompatActivity implements OnLoadMoreListener {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity);
        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<SampleModel> sampleModelList = new ArrayList<>();

        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));
        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));
        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));
        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));
        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));
        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));
        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));
        sampleModelList.add(new SampleModel("The drop","http://artsfuse.org/wp-content/uploads/2011/04/Adele-21-Album-Cover-Art-500x500.png"));

        AdapterBuilder.Builder builder = new AdapterBuilder.Builder()
                .normalItemResource(R.layout.item)
                .withHolder(NormalItemViewHolder.class)
                .headerItemResource(R.layout.item_header)
                .withHolder(HeaderItemViewHolder.class)
                .footerItemResource(R.layout.item_footer)
                .withHolder(FooterItemViewHolder.class)
                .setOnLoadMoreListener(this)
                .attachRecyclerView(recyclerView);

        AdapterBuilder adapterBuilder = builder.build();

        adapterBuilder.bindHeader(new Object());
        adapterBuilder.bindFooter(new Object());
        adapterBuilder.bindData(sampleModelList);

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

        adapterBuilder.setSpanSizeLookup(1, 2); // For Grid

        adapterBuilder.enableHeader(true);
        adapterBuilder.enableFooter(true);

        recyclerView.setAdapter(adapterBuilder);
    }

    @Override
    public void onLoadMore(int page) {

    }
}
