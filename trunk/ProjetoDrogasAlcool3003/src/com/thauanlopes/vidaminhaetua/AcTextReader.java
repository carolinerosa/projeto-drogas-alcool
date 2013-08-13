package com.thauanlopes.vidaminhaetua;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.game.ThauanFramework.MinhasCoisas;

public class AcTextReader extends Activity {
	
	public boolean setado = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MinhasCoisas.setCurrentActivity(this);

		// Screen adjustments
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_ac_text_reader);
		
		if(!setado){
		TextView titleView = (TextView) findViewById(R.id.textView_readerTitle);
		titleView.setText(LeitorDeHistorias.getInstance().getHistoriaAtual().getTitulo());
		titleView.setBackgroundColor(Color.rgb(50, 120, 50));
		titleView.setTextColor(Color.WHITE);
		
		TextView bodyView = (TextView) findViewById(R.id.textView_readerBody);
		bodyView.setText(LeitorDeHistorias.getInstance().getHistoriaAtual().getTexto());
		
		
		TextView autorView = (TextView) findViewById(R.id.textView_readerAuthor);
		autorView.setText(LeitorDeHistorias.getInstance().getHistoriaAtual().getAutor());
		
		setado = true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ac_text_reader, menu);
		return true;
	}

}
