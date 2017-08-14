package therealandroid.github.com.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import therealandroid.github.com.headerfooteradapter.R;


public class HeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView message;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        message = (TextView) itemView.findViewById(R.id.header_title);
    }
}