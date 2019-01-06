package com.car.insurance.online.ca;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Preferences {

    private static final String
            PREFERENCES_NAME = "APP_PREFERENCES";
    public static final String KEY_COUNTER = "ClickCounter";
    public static final String KEY_LIMIT= "click_limit";
    public static final String KEY_BANNER = "banner_id";
    public static final String KEY_INTER = "inter_id";
    public static final String KEY_DELAY1 = "delayban";
    public static final String KEY_DELAY2 = "delayin";

    public static final String AD_APP_ID = "ca-app-pub-1468953847768359~3039838954";
    public static final String BANNER_ID1 = "ca-app-pub-1468953847768359/1663371629";
    public static final String BANNER_ID2 = "ca-app-pub-1468953847768359/1663371629";
    public static final String INTER_ID = "ca-app-pub-1468953847768359/8925772213";


    public static final String BANNER_TEST = "ca-app-pub-3940256099942544/6300978111";


    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private final Context _context;

    public Preferences(Context context) {
        this._context = context;
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return _context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferencess() {
        return PreferenceManager.getDefaultSharedPreferences(_context);
    }

    public void setClickCounter(){
        editor = prefs.edit();
        int a = prefs.getInt(KEY_COUNTER, 0);
        a = a+1;
        editor.putInt(KEY_COUNTER, a);
        editor.apply();
    }

    public void resetClickCounter(){
        editor.putInt(KEY_COUNTER, 0);
        editor.apply();
    }

    public int getClickCounter(){
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getInt(KEY_COUNTER, 0);
    }

    public void saveBannerID( String value) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor = prefs.edit();
        editor.putString(KEY_BANNER, value);
        editor.apply();
    }

    public void saveInterID( String value) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor = prefs.edit();
        editor.putString(KEY_INTER, value);
        editor.apply();
    }

    public void saveLimit( String value) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor = prefs.edit();
        editor.putString(KEY_LIMIT, value);
        editor.apply();
    }

    public String getBannerID() {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getString(KEY_BANNER,"");
    }

    public String getInterID() {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getString(KEY_INTER,"");
    }

    public String getLimit() {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getString(KEY_LIMIT, "");
    }

    public void saveDelay( String value1, String value2) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor = prefs.edit();
        editor.putString(KEY_DELAY1, value1);
        editor.putString(KEY_DELAY2, value1);
        editor.apply();
    }
    public String getdelayban() {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getString(KEY_DELAY1, "");
    }
    public String getdelayin() {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getString(KEY_DELAY2, "");
    }
}