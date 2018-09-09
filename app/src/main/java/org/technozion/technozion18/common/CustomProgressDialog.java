package org.technozion.technozion18.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import org.technozion.technozion18.R;

public class CustomProgressDialog extends ProgressDialog{

    public CustomProgressDialog(Context context){
        super(context);
        getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        setIndeterminate(true);
        setCancelable(false);
    }

    @Override
    public void show(){
        super.show();
        setContentView(R.layout.progress_dialog_layout);
    }
}
