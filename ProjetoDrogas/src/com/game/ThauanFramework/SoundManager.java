package com.game.ThauanFramework;

import java.util.HashMap;
import java.util.Set;

import javax.xml.transform.Source;

import android.R.raw;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;

public class SoundManager {

	private String TAG = "Sound Manager";

	private AudioManager audioManager;

	private HashMap<String, MediaPlayer> songs;
	private static SoundManager instance;

	private SoundManager() {
		audioManager = (AudioManager)MinhasCoisas.getCurrentActivity().getSystemService(Context.AUDIO_SERVICE);
		this.songs = new HashMap<String, MediaPlayer>();
	}
	public static SoundManager getInstance()
	{
		if(instance == null)
		{
			instance = new SoundManager();
		}

		return instance;
	}

	public void playSound(final int source, String name)
	{
		final MediaPlayer mp = MediaPlayer.create(MinhasCoisas.getCurrentActivity(), source);

		try{
			mp.start();
			
			this.songs.put(name, mp);
			
		}catch(Exception e)
		{
			mp.stop();
			Log.i(TAG, "Erro no som"); 
		}
	}

	public void playSound(final int source, String name, int volume)
	{
		final MediaPlayer mp = MediaPlayer.create(MinhasCoisas.getCurrentActivity(), source);
		try{
			mp.start();
			mp.setVolume(volume, volume);
			this.songs.put(name, mp);
			
		}catch(Exception e)
		{
			mp.stop();
			Log.i(TAG, "Erro no som"); 
		}
	}
	public void playLoopingSound(final int source, String name)
	{
		final MediaPlayer mp = MediaPlayer.create(MinhasCoisas.getCurrentActivity(), source);

		try{
			mp.start();
			mp.setLooping(true);
			this.songs.put(name, mp);
		}catch(Exception e)
		{
			mp.stop();
			Log.i(TAG, "Erro no som"); 
		}
	}
	public void StopSong(String name)
	{
		try{
			this.songs.get(name).stop();
		}catch(Exception e)
		{
			Log.i(TAG, "Erro ao tentar parar som");
		}
	}
	public void StopAllSongs()
	{
		Set<String> chaves = songs.keySet();  
        for (String chave : chaves)  
        {  
            if(chave != null)  
                songs.get(chave).stop();  
        } 
	}
	
	public MediaPlayer GetSong(String key)
	{
		MediaPlayer mp = songs.get(key);
		return mp;
	}
	
	public void setVolumeForAll(int left, int right)
	{
		Set<String> chaves = songs.keySet();  
        for (String chave : chaves)  
        {  
            if(chave != null)  
                songs.get(chave).setVolume(left, right);  
        } 
	}
	public void setVolume(String key, int left, int right)
	{
		songs.get(key).setVolume(left, right);
	}
	

}
