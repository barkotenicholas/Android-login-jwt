package barkote.emo.login;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Helper {
    public SharedPreferences.Editor editor;
    public SharedPreferences pref;

    public Helper(Context ctx) {
        pref  = ctx.getSharedPreferences("MyPref", MODE_PRIVATE);
      editor = pref.edit();
    }

    public void savetoken(String Token)
    {
        editor.putBoolean("loggedin", true); // Storing boolean - true/false
        editor.putString("token", Token); // Storing string

    }

    public String getToken()
    {

        return pref.getString("token", null);
    }

    public boolean isloggedin()
    {

        return pref.getBoolean("loggedin",false);

    }
    public void logout()
    {
        editor.clear();
        editor.commit(); // commit changes
    }


}
