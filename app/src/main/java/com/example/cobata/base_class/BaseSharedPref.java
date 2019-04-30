package com.example.cobata.base_class;


import android.content.Context;
import android.content.SharedPreferences;

public class BaseSharedPref {

    public static final String SHARED ="shared";
    public static final String URL ="url";
    private Context context = null;

    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;



    public static void saveSPString(Context context, String keySP, String value){
        sp = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        spEditor = sp.edit();
        spEditor.putString(keySP, value);
        spEditor.commit();
    }


    public static String getSpString(Context context, String keySP){
        sp = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        spEditor = sp.edit();
        return sp.getString(keySP, "");
    }
}
