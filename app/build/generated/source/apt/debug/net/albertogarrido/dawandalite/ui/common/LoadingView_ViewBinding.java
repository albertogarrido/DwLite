// Generated code from Butter Knife. Do not modify!
package net.albertogarrido.dawandalite.ui.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import net.albertogarrido.dawandalite.R;

public class LoadingView_ViewBinding implements Unbinder {
  private LoadingView target;

  @UiThread
  public LoadingView_ViewBinding(LoadingView target) {
    this(target, target);
  }

  @UiThread
  public LoadingView_ViewBinding(LoadingView target, View source) {
    this.target = target;

    target.loadingText = Utils.findRequiredViewAsType(source, R.id.loading_text, "field 'loadingText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoadingView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loadingText = null;
  }
}
