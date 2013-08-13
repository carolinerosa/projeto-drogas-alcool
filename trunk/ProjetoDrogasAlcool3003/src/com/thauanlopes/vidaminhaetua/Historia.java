package com.thauanlopes.vidaminhaetua;

public class Historia {
	
	private String autor;
	private String texto;
	private String titulo;
	
	
	public Historia(String autor, String Titulo, String texto)
	{
		this.autor = autor;
		this.titulo = Titulo;
		this.texto = texto;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String genero) {
		autor = genero;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String historia) {
		texto = historia;
	}
}
