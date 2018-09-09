package org.technozion.technozion18.common;

public interface BaseViewAction {
    void showMessage(String message);
    void showNetworkError(String message);
    void showLoader();
    void hideLoader();
}
