package com.example.gw.traffic.db;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by gw on 2019/2/28.
 */

public class DBUtil {
    public static void setValue(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("Setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getValue(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("Setting", MODE_PRIVATE);
        return sp.getString(key, null);
    }
}
