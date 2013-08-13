package com.thauanlopes.vidaminhaetua;

import com.game.ThauanFramework.MinhasCoisas;
import com.thauanlopes.vidaminhaetua.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AcSend extends Activity {

	Intent sendEmail;
	EditText editText_emailBody;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MinhasCoisas.setCurrentActivity(this);

		// Screen adjustments
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		setContentView(R.layout.activity_ac_send);
		
		editText_emailBody = (EditText) findViewById(R.id.editText_Message);
		
		if(sendEmail == null){
		sendEmail = new Intent(Intent.ACTION_SEND);
		sendEmail.setType("message/rfc822");
		sendEmail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"vidaminhaetua@gmail.com"});
		
	}
		
	Button sendBt = (Button) findViewById(R.id.button_send);
	sendBt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Vida Minha - Minha História");
			sendEmail.putExtra(Intent.EXTRA_TEXT   , editText_emailBody.getText().toString());
			try {
			    startActivity(Intent.createChooser(sendEmail, "Send Mail"));
			} catch (android.content.ActivityNotFoundException ex) {
			   
			}
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ac_send, menu);
	
			
		return true;
	}
	
	
	

}
