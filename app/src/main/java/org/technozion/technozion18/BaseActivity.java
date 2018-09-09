package org.technozion.technozion18;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.technozion.technozion18.common.BaseViewAction;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity implements BaseViewAction{

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoader();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {
        showMessage("Something went wrong. Please check your connection.");
    }

    @Override
    public void showLoader() {
        if(!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void hideLoader() {
        if(dialog.isShowing())
            dialog.hide();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void initLoader(){
        dialog = new ProgressDialog(this);
        dialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.progress_dialog_layout);
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        if (android.os.Build.VERSION.SDK_INT>19) {
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

        }
        else{
            super.attachBaseContext(newBase);
        }


    }
}
