package com.thauanlopes.vidaminhaetua;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.game.ThauanFramework.MinhasCoisas;
import com.thauanlopes.vidaminhaetua.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

		if (sendEmail == null) {
			sendEmail = new Intent(Intent.ACTION_SEND);
			sendEmail.setType("message/rfc822");
			sendEmail.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "vidaminhaetua@gmail.com" });

		}

		Button sendBt = (Button) findViewById(R.id.button_send);
		sendBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				enviaEmailSimples();
			}
		});
	}

	public void enviaEmailSimples() {

		SimpleEmail email = new SimpleEmail();
		email.setDebug(true);

		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio
												// do e-mail
		try {
			email.addTo("vidaminhaetua@gmail.com");
			// destinatário
			email.setFrom("carolinerosa.nave@gmail.com", "Projeto Drogas"); // remetente
			email.setSubject("Vida Minha - Minha História"); // assunto do
																// e-mail
			email.setMsg(editText_emailBody.getText().toString()); // conteudo
																	// do
																	// e-mail
			email.setAuthentication("carolinerosa.nave", "171995carol842");
			email.setSmtpPort(465);
			email.setSSL(true);
			email.setTLS(true);
			email.send();
			setContentView(R.layout.activity_ac_send2);

		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ac_send, menu);

		return true;
	}

}
