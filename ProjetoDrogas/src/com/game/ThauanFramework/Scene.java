package com.game.ThauanFramework;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public abstract class Scene extends View implements Runnable{

	private String TAG = "GameScene";
	
	private boolean running = true;
	
	// Thread Interval
	public int interval = 10;
	Thread mainThread;
	// To catch all the scene game objects and run their Update and Draw methods.
	public List<GameObject> gameObjects;
	// To catch all the scene game objects and null them.
	public static List<GameObject> cemetery;
	
	public static Resources resources;
	
	public static int canvasHeight;
	public static int canvasWidth;

	public static long deltaTime;
	public static long lastTimeCount;
	
	public Scene(Context context) 
	{
		super(context);	
		running = true;
		
		this.setFocusable(true);
		
		resources = context.getResources();
		
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		canvasWidth = w;
		canvasHeight = h;
	}
	
	public abstract boolean onTouchEvent(MotionEvent event);

	//------------------------------------------------------------- START
	public void Start()
	{
		lastTimeCount = System.currentTimeMillis();	
	}
	//------------------------------------------------------------- UPDATE
	public void Update()
	{
		deltaTime = (System.currentTimeMillis() - lastTimeCount);
		lastTimeCount = System.currentTimeMillis();
		
		// Gameobjects UPDATE
		if(!gameObjects.isEmpty())
		{
			for(GameObject gb : gameObjects)
			{
				gb.Update();
			}
		}
	}
	
	//Draw
	@Override
	public void draw(Canvas canvas) 
	{
		super.draw(canvas);

			if(!cemetery.isEmpty())
			{
				for(GameObject gb : cemetery)
				{
					gb.Destroy();
					gb = null;
				}
			}
			cemetery.clear();		
				
				
		if(!gameObjects.isEmpty())
		{
			for(GameObject go : gameObjects)
			{
				go.Draw(canvas);
				
			}
		}
	}
	
	public void SetAlive(boolean bool)
	{
		this.running = bool;
	}
		
	@Override
	public void run() {
		this.Start();
		
		while(running)
		{
			try
			{
				Thread.sleep(interval);
			}
			catch(InterruptedException e)
			{
				running = false;
				Log.i(TAG, "Erro no processo de Thread", e);
			}
			
			Update();
			
			// Reload drawables on the screen
			postInvalidate();			
		}

	}
}
