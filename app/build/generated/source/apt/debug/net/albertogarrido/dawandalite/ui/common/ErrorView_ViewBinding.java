// Generated code from Butter Knife. Do not modify!
package net.albertogarrido.dawandalite.ui.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import net.albertogarrido.dawandalite.R;

public class ErrorView_ViewBinding implements Unbinder {
  private ErrorView target;

  private View view2131558569;

  @UiThread
  public ErrorView_ViewBinding(ErrorView target) {
    this(target, target);
  }

  @UiThread
  public ErrorView_ViewBinding(final ErrorView target, View source) {
    this.target = target;

    View view;
    target.image = Utils.findRequiredViewAsType(source, R.id.error_image, "field 'image'", ImageView.class);
    target.message = Utils.findRequiredViewAsType(source, R.id.error_message, "field 'message'", TextView.class);
    view = Utils.findRequiredView(source, R.id.error_retry, "field 'retry' and method 'onRetry'");
    target.retry = Utils.castView(view, R.id.error_retry, "field 'retry'", Button.class);
    view2131558569 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRetry(Utils.castParam(p0, "doClick", 0, "onRetry", 0, Button.class));
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ErrorView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
    target.message = null;
    target.retry = null;

    view2131558569.setOnClickListener(null);
    view2131558569 = null;
  }
}
