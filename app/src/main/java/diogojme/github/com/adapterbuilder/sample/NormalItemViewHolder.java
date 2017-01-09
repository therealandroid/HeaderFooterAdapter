package diogojme.github.com.adapterbuilder.sample;

import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import diogojme.github.com.adapterbuilder.HolderItemClickListener;
import diogojme.github.com.adapterbuilder.GenericItemViewHolder;
import diogojme.github.com.adapterbuilder.R;

/**
 * Created by diogojayme on 1/9/17.
 */

public class NormalItemViewHolder extends GenericItemViewHolder<SampleModel> {

    ImageView imageView;

    public NormalItemViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    @Override
    public void bindItem(SampleModel sampleModel) {
        Picasso.with(itemView.getContext()).load(sampleModel.getImage()).into(imageView);
    }

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
