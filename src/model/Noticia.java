package model;

import java.io.Serializable;

public class Noticia implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	private String titulo;
	private String texto;
	
	public Noticia() {}
	
	public Noticia(int id, String descricao, String titulo, String texto) {
		setId(id);
		setDescricao(descricao);
		setTitulo(titulo);
		setTexto(texto);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
