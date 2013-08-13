package com.game.ThauanFramework;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;

public class MinhasCoisas{

	private static final String TAG = "Minhas Coisas";

	private static Activity currentActivity;
	private static Resources res;
	private static Random random;
	private static Paint paint;

	private static int canvasWidth;
	private static int canvasHeight;
	
	private static boolean abriuActivity = false;

	public static boolean isAbriuActivity() {
		return abriuActivity;
	}
	public static void setAbriuActivity(boolean abriuActivity) {
		MinhasCoisas.abriuActivity = abriuActivity;
	}
	public static float getHeightPercent(boolean integer)
	{
		return MinhasCoisas.getCanvasWidth()/100;	
	}
	public static float getWidhtPercent(boolean integer)
	{
		return	MinhasCoisas.getCanvasWidth()/100;

	}

	public static int getCanvasWidth() {
		return canvasWidth;
	}
	public static void setCanvasWidth(int canvasWidth) {
		MinhasCoisas.canvasWidth = canvasWidth;
	}
	public static int getCanvasHeight() {
		return canvasHeight;
	}
	public static void setCanvasHeight(int canvasHeight) {
		MinhasCoisas.canvasHeight = canvasHeight;
	}


	public static Random getRandom()
	{
		if(random == null){ random = new Random(); }
		return random;
	}
	public static Paint getPaint()
	{
		if(paint == null)
		{
			paint = new Paint();
		}

		return paint;
	}
	public static Resources getResources()
	{
		if(res == null){ res = currentActivity.getResources(); }
		return res;
	}
	public static Activity getCurrentActivity() {
		return currentActivity;
	}
	public static void setCurrentActivity(Activity act)
	{
		currentActivity = act;
		res = act.getResources();
		Log.i(TAG, "setou current activity");
	}
	public static void ChangeActivity(Class<?> nova)
	{
		abriuActivity = false;
		Intent intent = new Intent(currentActivity, nova);
		currentActivity.startActivity(intent);
	}
	
	public static void Show(final String msg)
	{
		currentActivity.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				try{
				Toast.makeText(currentActivity, msg, Toast.LENGTH_SHORT).show();	
				}catch(Exception e)
				{
					Log.i(TAG,  "Não deu pra fazer aparecer o toast");
				}
			}
		});
	}

}
