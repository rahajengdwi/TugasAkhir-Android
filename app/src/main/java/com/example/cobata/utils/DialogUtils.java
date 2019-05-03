package com.example.cobata.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.example.cobata.R;
import com.example.cobata.interfaces.ReturnListener;

import androidx.appcompat.app.AlertDialog;

public class DialogUtils {
    public static void showSingleChoiceDialog(Context context, String title, final String[] list, final ReturnListener listener) {
        String single_choice_selected = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDataReturn(i);
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("CANCEL", null);
        builder.show();
    }
}
