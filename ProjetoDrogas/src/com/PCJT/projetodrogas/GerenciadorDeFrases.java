package com.PCJT.projetodrogas;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.game.ThauanFramework.MinhasCoisas;
import com.game.ThauanFramework.SaveManager;

public class GerenciadorDeFrases {

	private final String TAG = "Gerenciador de Mensagens de Outras Pessoas";
	private String fraseAtual;
	public String getFraseAtual()
	{
		return this.fraseAtual;
	}
	public void setFraseAtual(String n)
	{
		this.fraseAtual = n;
	}
	private ArrayList<String> outMessages = new ArrayList<String>();
	public ArrayList<String> getOutMessages()
	{
		return this.outMessages;
	}
	private static boolean usada = false;
	private String versao = "-1";

	private final String urlStr = "https://dl.dropboxusercontent.com/u/42561376/ProjetoDrogas_Mensagens.txt";
	private URL url;

	private final String forSave_Messages = "ProjetoDrogas_OutMessages";
	private final String forSave_Usada = "ProjetoDrogas_HaMensagensAqui";
	private final String forSave_Versao = "ProjetoDrogas_Versao";

	private static GerenciadorDeFrases instance;

	private GerenciadorDeFrases() {

		this.usada = SaveManager.getInstance().LoadBool(this.forSave_Usada);

		try {
			this.url = new URL(this.urlStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (usada) {

			ArrayList<String> frasesSalvas = (SaveManager.getInstance()
					.LoadStringSet(this.forSave_Messages));
			outMessages = frasesSalvas;
			for (String str : outMessages) {
				Log.i(TAG, str);
			}
			Log.i(TAG, "Instancia: GerenciadorDeFrases");

			this.versao = SaveManager.getInstance().LoadString(
					this.forSave_Versao);

		} else {

			RenovarBancoDeDados();
		}
	}

	public static GerenciadorDeFrases getInstance() {
		if (instance == null) {
			instance = new GerenciadorDeFrases();
		}
		return instance;
	}

	private void saveFrases() {
		Set<String> setFrases = new HashSet<String>(outMessages);
		SaveManager.getInstance().SaveStringSet(this.forSave_Messages,
				setFrases);
	}

	public String getMessage(int pos) {

		if (outMessages.size() != 0) {

			String frase = outMessages.get(pos).toString();
			outMessages.remove(pos);

			saveFrases();
			return frase;

		} else {
			MinhasCoisas
					.Show("Por favor, renove ou resete o banco de outMessages");
		}

		return "...";
	}

	public String getRandomMessage() {

		if (outMessages.size() != 0) {

			int i = MinhasCoisas.getRandom().nextInt(outMessages.size());
			String frase = outMessages.get(i).toString();
			
			return frase;

		} else {
			MinhasCoisas.Show("Por favor, renove o banco de Mensagens");
		}

		return "...";
	}

	public void RenovarBancoDeDados() {
		try {
			InputStream is = this.url.openStream();
			Scanner scan = new Scanner(is);

			String versaoStr = scan.nextLine();

			Log.i(TAG, versao + " " + versaoStr);
			if (!(this.versao.equals(versaoStr))) {
				
				this.outMessages.clear();
				
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					outMessages.add(line);
				}
				
				scan.close();
				is.close();
				
				this.versao = versaoStr;
				
				Log.i(TAG, "Frases renovadas");
				
				MinhasCoisas
						.Show("Pronto. Agora você possui novas outMessages!");

				SaveManager.getInstance().SaveString(this.forSave_Versao, versao);
				SaveManager.getInstance().SaveBoolean(this.forSave_Usada, true);
				saveFrases();
			}else
			{
				// Alert Box
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
						MinhasCoisas.getCurrentActivity());
				alertBuilder
						.setMessage("Você já possui todas as Mensagens.");
				alertBuilder.setTitle("Sem Novas Mensagens");
				
				alertBuilder.setNeutralButton("Fechar", null);

				final AlertDialog alert = alertBuilder.create();
				MinhasCoisas.getCurrentActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						alert.show();
					}
				});
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			Log.i(TAG, "Erro ao capturar stream do arquivo na internet");
			
			// Alert Box
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
					MinhasCoisas.getCurrentActivity());
			alertBuilder
					.setMessage("Não foi possível realizar o download de mensagens. Por favor, verifique a sua conexão à internet.");
			alertBuilder.setTitle("FALTOU ALGUMA COISA");
			alertBuilder.setPositiveButton("Tentar De Novo",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							GerenciadorDeFrases.getInstance().RenovarBancoDeDados();
						}
					});

			alertBuilder.setNegativeButton("Fechar",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
			final AlertDialog alert = alertBuilder.create();
			MinhasCoisas.getCurrentActivity().runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					alert.show();
				}
			});
			
			e.printStackTrace();
		}
	}
}
