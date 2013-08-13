package com.thauanlopes.vidaminhaetua;

import com.thauanlopes.vidaminhaetua.R;
import com.game.ThauanFramework.MinhasCoisas;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AcMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MinhasCoisas.setCurrentActivity(this);

		// Screen adjustments
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_ac_menu);
		
		Button bt_mensagens = (Button)	findViewById(R.id.button_readMessages);
		bt_mensagens.setBackgroundColor(Color.rgb(50, 120, 50));
		
		Button bt_enviarMensagem = (Button)	findViewById(R.id.button_sendMessage);
		bt_enviarMensagem.setBackgroundColor(Color.rgb(50, 120, 50));
		
		Button bt_videos = (Button)	findViewById(R.id.button_videos);
		bt_videos.setBackgroundColor(Color.rgb(50, 120, 50));

		bt_mensagens.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				MinhasCoisas.ChangeActivity(AcMain.class);
			}
		});
		
		bt_enviarMensagem.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				MinhasCoisas.ChangeActivity(AcSend.class);

			}
		});

		bt_videos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ac_menu, menu);
		return true;
	}

}
