package net.albertogarrido.dawandalite.ui.categories;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.albertogarrido.dawandalite.R;
import net.albertogarrido.dawandalite.model.Category;
import net.albertogarrido.dawandalite.ui.common.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private List<Category> categoriesList;
    private Context context;
    private RecyclerItemClickListener mListener;

    public CategoriesAdapter(List<Category> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = categoriesList.get(position);

        Callback imageLoadedCallback = prepareImageLoadedCallback(position);

        Picasso.with(context)
                .load("http:" + category.imgUrl())
                .into(holder.categoryImage, imageLoadedCallback);
        holder.categoryName.setText(category.name());
    }

    public void addRecyclerItemClickListener(RecyclerItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;

        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_item, viewGroup, false);

        return new CategoryViewHolder(itemView, mListener);
    }

    private Callback prepareImageLoadedCallback(final int adapterPosition) {
        return new Callback() {
            @Override
            public void onSuccess() {
//                categoriesList.get(adapterPosition).setImageLoaded(true);
            }

            @Override
            public void onError() {
//                categoriesList.get(adapterPosition).setImageLoaded(false);
            }
        };
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RecyclerItemClickListener listener;

        @BindView(R.id.root_view)
        protected ViewGroup rootView;

        @BindView(R.id.category_image)
        protected ImageView categoryImage;

        @BindView(R.id.category_name)
        protected TextView categoryName;


        public CategoryViewHolder(@NonNull View view, @NonNull RecyclerItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View viewCaller) {
            listener.onItemClick(viewCaller, categoryImage, getAdapterPosition());
        }
    }
}
