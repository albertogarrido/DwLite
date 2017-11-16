// Generated code from Butter Knife. Do not modify!
package net.albertogarrido.dawandalite.ui.products.detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import net.albertogarrido.dawandalite.R;

public class ProductDetailActivity_ViewBinding implements Unbinder {
  private ProductDetailActivity target;

  @UiThread
  public ProductDetailActivity_ViewBinding(ProductDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductDetailActivity_ViewBinding(ProductDetailActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbarLayout = Utils.findRequiredViewAsType(source, R.id.toolbar_layout, "field 'toolbarLayout'", CollapsingToolbarLayout.class);
    target.productImage = Utils.findRequiredViewAsType(source, R.id.product_image_toolbar, "field 'productImage'", ImageView.class);
    target.productPrice = Utils.findRequiredViewAsType(source, R.id.product_price, "field 'productPrice'", TextView.class);
    target.productBadge = Utils.findRequiredViewAsType(source, R.id.product_badge, "field 'productBadge'", TextView.class);
    target.productTitle = Utils.findRequiredViewAsType(source, R.id.product_title, "field 'productTitle'", TextView.class);
    target.productSeller = Utils.findRequiredViewAsType(source, R.id.product_seller, "field 'productSeller'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.toolbarLayout = null;
    target.productImage = null;
    target.productPrice = null;
    target.productBadge = null;
    target.productTitle = null;
    target.productSeller = null;
  }
}
