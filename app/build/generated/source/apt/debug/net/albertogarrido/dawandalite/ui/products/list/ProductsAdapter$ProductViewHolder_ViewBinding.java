// Generated code from Butter Knife. Do not modify!
package net.albertogarrido.dawandalite.ui.products.list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import net.albertogarrido.dawandalite.R;

public class ProductsAdapter$ProductViewHolder_ViewBinding implements Unbinder {
  private ProductsAdapter.ProductViewHolder target;

  @UiThread
  public ProductsAdapter$ProductViewHolder_ViewBinding(ProductsAdapter.ProductViewHolder target,
      View source) {
    this.target = target;

    target.rootView = Utils.findRequiredViewAsType(source, R.id.root_view, "field 'rootView'", ViewGroup.class);
    target.image = Utils.findRequiredViewAsType(source, R.id.product_image, "field 'image'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.product_title, "field 'title'", TextView.class);
    target.price = Utils.findRequiredViewAsType(source, R.id.product_price, "field 'price'", TextView.class);
    target.badge = Utils.findRequiredViewAsType(source, R.id.product_badge, "field 'badge'", TextView.class);
    target.seller = Utils.findRequiredViewAsType(source, R.id.product_seller, "field 'seller'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductsAdapter.ProductViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rootView = null;
    target.image = null;
    target.title = null;
    target.price = null;
    target.badge = null;
    target.seller = null;
  }
}
