package barkote.emo.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class    Helper {
    public SharedPreferences pref;
    public Context ctx;

    public Helper(Context ctx) {
        this.ctx = ctx;
        pref = ctx.getSharedPreferences("pref",Context.MODE_PRIVATE);
    }

    public void savetoken(String Token,Context ctx)
    {
         SharedPreferences.Editor editor;


        editor = pref.edit();
        editor.putBoolean("loggedin", true); // Storing boolean - true/false
        editor.putString("token", Token); // Storing string

        editor.commit();
    }

    public String getToken(Context ctx)
    {
        SharedPreferences.Editor editor;
        return pref.getString("token", null);
    }

    public boolean isloggedin(Context ctx)
    {

        return pref.getBoolean("loggedin",false);

    }
    public void logout()
    {
        SharedPreferences.Editor editor;


        editor = pref.edit();

        editor.clear();
        editor.commit();
    }


}
