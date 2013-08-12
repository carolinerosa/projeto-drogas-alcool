package com.PCJT.projetodrogas;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.backup.FileBackupHelper;
import android.util.Log;

public class LeitorDeHistorias {

	private final String TAG_Texto = "mensagem";
	private ArrayList<Historia> historias = new ArrayList<Historia>();
	
	private final String url = "http://dl.dropboxusercontent.com/u/42561376/ProjetoDrogas_Alcohol_Mensagens.xml";
	
	private final String TAG = "LEITOR DE MENSAGENS";
	private int versao = -1;

	private LeitorDeHistorias()
	{
		Document doc = null;
		try{  
			InputStream is = new URL(url).openStream();
			Log.i(TAG, "Arquivo pegado com sucesso");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			doc = docBuilder.parse(is);
					
		}catch(Exception e)
		{
			Log.i(TAG, "Erro ao criar documento e passar para objetos de historia");
		}
		
		PassaParaHistoria(doc, historias);
	}
	private static LeitorDeHistorias instance;
	public static LeitorDeHistorias getInstance()
	{
		if(instance == null)
		{
			instance = new LeitorDeHistorias();
		}
		
		return instance;
	}
	
	public ArrayList<Historia> Ler()
	{
		
		Document doc = null;
		try{  
			InputStream is = new URL(url).openStream();
			Log.i(TAG, "Arquivo pegado com sucesso");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			doc = docBuilder.parse(is);
			Log.i(TAG, "Doc criado com sucesso");
					
		}catch(Exception e)
		{
			Log.i(TAG, "Erro ao criar documento e passar para objetos de historia");
		}
		
		if(doc != null){
		PassaParaHistoria(doc, historias);
		}else
		{
			Log.i(TAG, "Doc nulo");
		}
		
		return historias;
	}
	
	private void PassaParaHistoria(Document doc, ArrayList<Historia> historias)
	{
		NodeList ndList = doc.getElementsByTagName(TAG_Texto);
		
		historias.clear();
		for (int i = 0; i < ndList.getLength(); ++i)
		{	
			Log.i(TAG, "mensagem");
			String mensagem = (String) ndList.item(i).getTextContent();
	        Historia h = new Historia(mensagem);
	        // --------- Poe no Array
	        
	        historias.add(h);
		}
	}
}
