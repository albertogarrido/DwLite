package net.albertogarrido.dawandalite.ui.products.list;


import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.albertogarrido.dawandalite.R;
import net.albertogarrido.dawandalite.model.Product;
import net.albertogarrido.dawandalite.ui.common.RecyclerItemClickListener;
import net.albertogarrido.dawandalite.utilities.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> productsList;
    private Context context;
    private RecyclerItemClickListener mListener;

    public ProductsAdapter(List<Product> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productsList.get(position);

        Picasso.with(context)
                .load("http:" + product.images().mainImg())
                .into(holder.image);

        ViewCompat.setTransitionName(holder.image, String.valueOf(product.id()));

        holder.title.setText(product.title());
        holder.seller.setText(product.seller().username());
        holder.price.setText(product.price().formatPrice());

        if (product.badge() != null && !TextUtils.isEmpty(product.badge())) {
            holder.badge.setText(StringUtils.capitalizeFirstWord(product.badge()));
            if (product.badge().equalsIgnoreCase(Product.BADGE_NEW)) {
                holder.badge.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBadgeNew));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    holder.badge.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.ic_new_releases_white_18dp),
                            null, null, null);
                }
            } else if (product.badge().equalsIgnoreCase(Product.BADGE_EXPRESS)) {
                holder.badge.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBadgeExpress));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    holder.badge.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.ic_airport_shuttle_white_18dp),
                            null, null, null);
                }
            } else if (product.badge().equalsIgnoreCase(Product.BADGE_SALE)) {
                holder.badge.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    holder.badge.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.ic_shopping_basket_white_18dp),
                            null, null, null);
                }
            }
            ViewCompat.setTransitionName(holder.badge, String.valueOf(product.id()) + product.badge());
        } else {
            holder.badge.setVisibility(View.GONE);
        }
    }

    public void addRecyclerItemClickListener(RecyclerItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;

        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);

        return new ProductViewHolder(itemView, mListener);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RecyclerItemClickListener listener;

        @BindView(R.id.root_view)
        protected ViewGroup rootView;

        @BindView(R.id.product_image)
        protected ImageView image;

        @BindView(R.id.product_title)
        protected TextView title;

        @BindView(R.id.product_price)
        protected TextView price;

        @BindView(R.id.product_badge)
        protected TextView badge;

        @BindView(R.id.product_seller)
        protected TextView seller;


        public ProductViewHolder(@NonNull View view, @NonNull RecyclerItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View viewCaller) {
            listener.onItemClick(viewCaller, image, getAdapterPosition());
        }
    }
}
