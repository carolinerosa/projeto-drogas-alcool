package com.game.ThauanFramework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.content.SharedPreferences;

public class SaveManager {

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	private SaveManager() {
		sp = MinhasCoisas.getCurrentActivity().getPreferences(
				MinhasCoisas.getCurrentActivity().MODE_PRIVATE);
		editor = sp.edit();
	}

	private static SaveManager instance;

	public static SaveManager getInstance() {
		if (instance == null) {
			instance = new SaveManager();
		}
		return instance;
	}

	// ------------------------ -----------------------
	// --------------------------- ----------------------

	public void SaveInt(String key, int value) {
		editor.putInt(key, value);
		editor.commit();
	}

	public void SaveFloat(String key, float value) {
		editor.putFloat(key, value);
		editor.commit();
	}

	public void SaveBoolean(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void SaveString(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	public void SaveStringSet(String key, Set<String> values) {
		
		StringBuilder builder = new StringBuilder();
		int contador = 0;
		for(String str : values)
		{
			builder.append(str + "/");
		}
		editor.putString(key, builder.toString());
		editor.commit();
	}

	// ---------------
	int defValue = 0;
	boolean defbool = false;

	// ---------------
	
	public int LoadInt(String key)
	{
		return sp.getInt(key, defValue);
	}	
	public float LoadFloat(String key)
	{
		return sp.getFloat(key, defValue);
	}	
	public boolean LoadBool(String key)
	{
		return sp.getBoolean(key, defbool);
	}
	public String LoadString(String key)
	{
		return sp.getString(key, "default");
	}
	public ArrayList LoadStringSet(String key)
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] strs = sp.getString(key, "default").split("/");
		for(int i = 0; i < strs.length - 1; i++)
		{
			list.add(strs[i]);
		}
		
		return list; 
	}
	
}
