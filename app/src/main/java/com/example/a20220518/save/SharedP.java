package com.example.a20220518.save;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedP {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Context context;
    public SharedP(Context c, String name){
        context=c;
        sp=context.getSharedPreferences(name,0);
        editor=sp.edit();
    }
    public void putValue(String key,String value){
        editor=sp.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public void removeValue(String key){
        editor=sp.edit();
        editor.remove(key);
        editor.commit();
    }
    public String getValue(String key){
        return sp.getString(key,null);
    }
}
