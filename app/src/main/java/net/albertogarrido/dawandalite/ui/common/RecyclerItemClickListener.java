package net.albertogarrido.dawandalite.ui.common;


import android.view.View;
import android.widget.ImageView;

public interface RecyclerItemClickListener {
    void onItemClick(View view, ImageView sharedImage, int position);
}
