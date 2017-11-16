// Generated code from Butter Knife. Do not modify!
package net.albertogarrido.dawandalite.ui.products.list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import net.albertogarrido.dawandalite.R;
import net.albertogarrido.dawandalite.ui.common.ErrorView;
import net.albertogarrido.dawandalite.ui.common.LoadingView;

public class ProductsListActivity_ViewBinding implements Unbinder {
  private ProductsListActivity target;

  @UiThread
  public ProductsListActivity_ViewBinding(ProductsListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductsListActivity_ViewBinding(ProductsListActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.errorView = Utils.findRequiredViewAsType(source, R.id.error_view, "field 'errorView'", ErrorView.class);
    target.loadingView = Utils.findRequiredViewAsType(source, R.id.loading_view, "field 'loadingView'", LoadingView.class);
    target.productsRecycler = Utils.findRequiredViewAsType(source, R.id.products_list, "field 'productsRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductsListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.errorView = null;
    target.loadingView = null;
    target.productsRecycler = null;
  }
}
