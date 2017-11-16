package net.albertogarrido.dawandalite.ui.common;


import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.albertogarrido.dawandalite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrorView extends LinearLayout {

    private RetryClickListener retryClickListener;

    public enum ErrorType {
        NETWORK, SERVER, NOT_FOUND, OTHER
    }

    private final Context context;

    @BindView(R.id.error_image)
    ImageView image;

    @BindView(R.id.error_message)
    TextView message;

    @BindView(R.id.error_retry)
    Button retry;

    public ErrorView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {
        inflate(context, R.layout.error_layout, this);
        if (isInEditMode()) return;
        ButterKnife.bind(this);
    }

    public void bind(ErrorType errorType, @Nullable String optionalMessage, @NonNull RetryClickListener retryClickListener) {
        this.retryClickListener = retryClickListener;
        switch (errorType) {
            case NETWORK:
                image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cloud_off_black_48dp));
                message.setText(optionalMessage != null ? optionalMessage : context.getResources().getString(R.string.error_network));
                break;
            case SERVER:
                image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_error_outline_black_48dp));
                message.setText(optionalMessage != null ? optionalMessage : context.getResources().getString(R.string.error_server));
                break;
            case NOT_FOUND:
                image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_search_black_48dp));
                message.setText(optionalMessage != null ? optionalMessage : context.getResources().getString(R.string.error_not_found));
                break;
            case OTHER:
                image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_warning_black_48dp));
                message.setText(optionalMessage != null ? optionalMessage : context.getResources().getString(R.string.error_unknown));
                break;
        }
    }

    @OnClick(R.id.error_retry)
    public void onRetry(Button button) {
        if (retryClickListener != null) {
            retryClickListener.onRetry();
        } else {
            throw new AssertionError("RetryClickedListener must be implemented");
        }
    }

    public interface RetryClickListener {
        void onRetry();
    }
}
