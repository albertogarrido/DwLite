package net.albertogarrido.dawandalite.ui.common;


import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.albertogarrido.dawandalite.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingView extends LinearLayout {

    private final Context context;

    @BindView(R.id.loading_text)
    TextView loadingText;

    public LoadingView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {
        inflate(context, R.layout.loading_layout, this);
        if (isInEditMode()) return;
        ButterKnife.bind(this);
    }


    public void bind(String string) {
        loadingText.setText(string);
    }
}
