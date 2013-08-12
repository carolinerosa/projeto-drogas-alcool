package com.PCJT.projetodrogas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.game.ThauanFramework.MinhasCoisas;
import com.game.ThauanFramework.SoundManager;

public class AcMain extends Activity {

	private static final String TAG = "Main Activity";

	private Button bt_getMsg;
	private Button bt_update;
	private Button bt_mute;
	private ScrollView scroll_msgScroll;
	
	// provisorio
	public static boolean mute = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MinhasCoisas.setCurrentActivity(this);

		// Screen adjustments
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Request landscape orientation
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Log.i(TAG,
				String.valueOf(getResources().getConfiguration().orientation));

		// if orientation is vertical
		if (getResources().getConfiguration().orientation == 1) {
			MinhasCoisas.setAbriuActivity(true);

		}

		Create();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Start();

		MinhasCoisas.setAbriuActivity(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ac_main, menu);
		return true;
	}

	private void Create() {
		if (MinhasCoisas.isAbriuActivity()) {
			Log.i(TAG, "CREATE");

			setContentView(R.layout.activity_ac_main);

			GerenciadorDeFrases.getInstance();

			SoundManager.getInstance().playLoopingSound(
					R.raw.popof_berceusounette, "one");
		}
	}

	private void Start() {
		if (MinhasCoisas.isAbriuActivity()) {
			Log.i(TAG, "START");
			
			this.bt_update = (Button) findViewById(R.id.button_atualizar);
			this.bt_mute = (Button) findViewById(R.id.button_mute);
			
			this.scroll_msgScroll = (ScrollView)
					findViewById(R.id.MessageScrollView);
			
			int contador = 0;
			ArrayList<View> tViews = new ArrayList<View>();
			
			for (Historia str : LeitorDeHistorias.getInstance().Ler()) {
				
				String shortStr = str.getTexto().substring(0, 100);
				TextView tv = new TextView(this);
				tv.setText(shortStr);
				tv.setId(contador);
				tv.setFocusable(true);
				tv.setGravity(Gravity.CENTER);

				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 70);
				lp.setMargins(0, 0, 0, 10);

				tv.setLayoutParams(lp);
				tv.invalidate();
				
				tv.setBackgroundColor(Color.BLUE);
				
				tv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						MinhasCoisas.ChangeActivity(AcTextReader.class);
					}
				});
				LinearLayout ll = (LinearLayout) findViewById(R.id.scroll_layout);
				ll.addView(tv);
				contador++;
			}

			bt_update.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					//GerenciadorDeFrases.getInstance().RenovarBancoDeDados();
					//LeitorDeHistorias.getInstance().Ler();

				}
			});

			bt_mute.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					//SoundManager.getInstance().StopSong("one");
					
					if(!AcMain.mute){
						Log.i(TAG, "mute");
						SoundManager.getInstance().setVolume("one", 0, 0);
						AcMain.mute = true;
					}else
					{
						Log.i(TAG, "not mute");
						SoundManager.getInstance().setVolume("one", 1, 1);
						AcMain.mute = false;
					}
				}
			});
		}
		
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SoundManager.getInstance().StopAllSongs();
	}

}
