package com.thauanlopes.vidaminhaetua;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.ThauanFramework.MinhasCoisas;
import com.game.ThauanFramework.SaveManager;

import android.util.Log;

public class LeitorDeHistorias {

	private ArrayList<Historia> historias = new ArrayList<Historia>();
	
	private static final String forSave_historias = "ProjetoDrogas_Historias";
	private static final String forSave_Usada = "ProjetoDrogas_ExistemHistoriasAqui";
	private static final String forSave_Versao = "ProjetoDrogas_Versao";
	
	private final String TAG_Historia = "pacote";
	private final String TAG_Titulo = "titulo";
	private final String TAG_Autor = "autor";
	private final String TAG_Texto = "mensagem";
	private final String TAG_Versao = "versao";
	
	private int ultimaVersao = -1;
	
	private final String url = "https://dl.dropboxusercontent.com/u/42561376/ProjetoDrogas_Alcohol_Mensagens.xml";
	
	private final String TAG = "LEITOR DE HISTORIAS";
	
	public ArrayList<Historia> getHistorias()
	{
		return this.historias;
	}
	private Historia historiaAtual = null;
	public Historia getHistoriaAtual()
	{
		return this.historiaAtual;
	}
	public void setHistoriaAtual(Historia historia)
	{
		this.historiaAtual = historia;
		
	}

	private LeitorDeHistorias()
	{
		this.ultimaVersao = SaveManager.getInstance().LoadInt(this.forSave_Versao);
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
	
	public boolean isUpdated()
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
		
		if(doc != null){
			
			NodeList versaoNodo = doc.getElementsByTagName(TAG_Versao);
			Log.i(TAG, versaoNodo.item(0).getTextContent());
			int versao = Integer.parseInt(versaoNodo.item(0).getTextContent());
			
			if(this.ultimaVersao != versao  || this.historias.size() == 0)
			{
				return true;
			}
			
		}
		return false;

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
					
		}catch(Exception e)
		{
			Log.i(TAG, "Erro ao criar documento e passar para objetos de historia");
		}
		
		if(doc != null){
			
			NodeList versaoNodo = doc.getElementsByTagName(TAG_Versao);
			Log.i(TAG, versaoNodo.item(0).getTextContent());
			int versao = Integer.parseInt(versaoNodo.item(0).getTextContent());
			
			if(this.ultimaVersao != versao  || this.historias.size() == 0)
			{
				this.ultimaVersao = versao;
				SaveManager.getInstance().SaveInt(this.forSave_Versao, versao);
				PassaParaHistoria(doc, historias);
			}else
			{
				return this.historias;
			}
			
		}
		return historias;
	}
	
	private void PassaParaHistoria(Document doc, ArrayList<Historia> historias)
	{
		
		NodeList ndListHistoria = doc.getElementsByTagName(this.TAG_Historia);
		NodeList ndListGenero = doc.getElementsByTagName(this.TAG_Autor);
		NodeList ndListTitulo = doc.getElementsByTagName(this.TAG_Titulo);
		NodeList ndListTexto = doc.getElementsByTagName(this.TAG_Texto);
		
		historias.clear();
		
		for (int i = 0; i < ndListHistoria.getLength(); ++i)
		{	
			Log.i(TAG, "Nodo");
			String autor = ndListGenero.item(i).getTextContent();
			String titulo = ndListTitulo.item(i).getTextContent();
			String texto = ndListTexto.item(i).getTextContent();
	        
	        Historia h = new Historia(autor, titulo, texto);
	        // --------- Poe no Array
	        
	        historias.add(h);
		}		
		
		for(Historia str : historias)
		{
			Log.i(TAG, str.getTitulo());
		}
		
	}
	public Historia getRandomHistoria()
	{
		int i = MinhasCoisas.getRandom().nextInt(historias.size());
		return this.historias.get(i);
	}
}
