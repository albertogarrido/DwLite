package net.albertogarrido.dawandalite.ui;


import android.support.annotation.Nullable;

import net.albertogarrido.dawandalite.ui.common.ErrorView;

public interface DaWandaLiteView {

    void showError(ErrorView.ErrorType errorType, @Nullable String optionalMessage);

    void hideError();

    void toggleLoading(boolean show);

    void toggleMainContent(boolean show);

}
