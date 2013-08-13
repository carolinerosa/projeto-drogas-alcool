package com.game.ThauanFramework;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {

	public String tag;	
	public Paint paint;
	public GameObject()
	{
		this.paint = new Paint();
	}
	public abstract void Update();
	
	public abstract void Draw(Canvas canvas);
	
	public abstract void Die();
	
	public abstract void Destroy();
}
