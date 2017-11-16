// Generated code from Butter Knife. Do not modify!
package net.albertogarrido.dawandalite.ui.categories;

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

public class CategoriesAdapter$CategoryViewHolder_ViewBinding implements Unbinder {
  private CategoriesAdapter.CategoryViewHolder target;

  @UiThread
  public CategoriesAdapter$CategoryViewHolder_ViewBinding(CategoriesAdapter.CategoryViewHolder target,
      View source) {
    this.target = target;

    target.rootView = Utils.findRequiredViewAsType(source, R.id.root_view, "field 'rootView'", ViewGroup.class);
    target.categoryImage = Utils.findRequiredViewAsType(source, R.id.category_image, "field 'categoryImage'", ImageView.class);
    target.categoryName = Utils.findRequiredViewAsType(source, R.id.category_name, "field 'categoryName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoriesAdapter.CategoryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rootView = null;
    target.categoryImage = null;
    target.categoryName = null;
  }
}
