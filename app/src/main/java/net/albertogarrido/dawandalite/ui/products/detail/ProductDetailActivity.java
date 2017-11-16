package net.albertogarrido.dawandalite.ui.products.detail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.albertogarrido.dawandalite.R;
import net.albertogarrido.dawandalite.model.Price;
import net.albertogarrido.dawandalite.model.Product;
import net.albertogarrido.dawandalite.model.Seller;
import net.albertogarrido.dawandalite.ui.products.list.ProductsListActivity;
import net.albertogarrido.dawandalite.utilities.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductDetailActivity extends AppCompatActivity {

    private static final String PRODUCT_PARCEL = "product_parcel";

    public static final String SHARED_ELEMENT_IMAGE = "shared_element";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.product_image_toolbar)
    ImageView productImage;

    @BindView(R.id.product_price)
    TextView productPrice;

    @BindView(R.id.product_badge)
    TextView productBadge;

    @BindView(R.id.product_title)
    TextView productTitle;

    @BindView(R.id.product_seller)
    TextView productSeller;

    private String productName;

    public static Intent intent(ProductsListActivity productsListActivity, Product product, String sharedImageName, String categoryName) {
        return new Intent(productsListActivity, ProductDetailActivity.class)
                .putExtra(PRODUCT_PARCEL, product)
                .putExtra(ProductsListActivity.CATEGORY_NAME, categoryName)
                .putExtra(SHARED_ELEMENT_IMAGE, sharedImageName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        configToolbar();
        configStatusbar();
        populateIncomingData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(productName != null ? productName : getResources().getString(R.string.product_detail));
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarLayout.setTitleEnabled(false);
    }

    private void configStatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimarySemiTransparentStatusBar, null));
            }
        }
    }

    private void populateIncomingData() {
        supportPostponeEnterTransition();

        Bundle extras = getIntent().getExtras();

        Product product = extras.getParcelable(PRODUCT_PARCEL);

        if (product == null) {
            throw new AssertionError(Product.class.getSimpleName() + " CAN NOT be null at this stage");
        }

        productName = product.title();


        productTitle.setText(productName);
        setPrice(product.price());
        setBadge(product.badge());
        setSeller(product.seller());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(SHARED_ELEMENT_IMAGE);
            productImage.setTransitionName(imageTransitionName);
        }


        Picasso.with(this)
                .load("http:" + product.images().mainImg())
                .noFade()
                .into(productImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                    }
                });
    }

    private void setSeller(Seller seller) {
        productSeller.setText(seller.username());
    }

    private void setBadge(@Nullable String badge) {
        if (badge != null && !TextUtils.isEmpty(badge)) {
            productBadge.setText(StringUtils.capitalizeFirstWord(badge));
            if (badge.equalsIgnoreCase(Product.BADGE_NEW)) {
                productBadge.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBadgeNew));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    productBadge.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            ContextCompat.getDrawable(this, R.drawable.ic_new_releases_white_24dp),
                            null, null, null);
                }
            } else if (badge.equalsIgnoreCase(Product.BADGE_EXPRESS)) {
                productBadge.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBadgeExpress));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    productBadge.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            ContextCompat.getDrawable(this, R.drawable.ic_airport_shuttle_white_24dp),
                            null, null, null);
                }
            } else if (badge.equalsIgnoreCase(Product.BADGE_SALE)) {
                productBadge.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    productBadge.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            ContextCompat.getDrawable(this, R.drawable.ic_shopping_basket_white_24dp),
                            null, null, null);
                }
            }
        } else {
            productBadge.setVisibility(View.GONE);
        }
    }

    private void setPrice(Price price) {
        productPrice.setText(price.formatPrice());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
