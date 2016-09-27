package br.com.diogojayme.autobindadapter.sample;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.diogojayme.autobindadapter.GenericItemViewHolder;
import br.com.diogojayme.autobindadapter.R;
import br.com.diogojayme.autobindadapter.ViewHolderClickListener;

/**
 * Created by diogojayme on 8/28/16.
 */
public class ItemViewHolder extends GenericItemViewHolder<Pokemon> {

    TextView name;
    ImageView image;

    public ItemViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.item_pokemon_name);
        image = (ImageView) itemView.findViewById(R.id.item_pokemon_image);
    }

    @Override
    public void bindItem(final Pokemon pokemon, final ViewHolderClickListener<Pokemon> listener) {
        name.setText(pokemon.getName());
        image.setImageResource(pokemon.getImage());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(pokemon, getAdapterPosition());
            }
        });
    }

}
