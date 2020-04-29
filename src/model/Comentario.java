package model;

import java.io.Serializable;


public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	private  int id;
	private String nome;
	private String texto;
	private int fk_noticia_id;
	public Comentario() {
		
	}
	
	public Comentario(int id, String nome, String texto) {
		setId(id);
		setNome(nome);
		setTexto(texto);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getFk_noticia_id() {
		return fk_noticia_id;
	}

	public void setFk_noticia_id(int fk_noticia_id) {
		this.fk_noticia_id = fk_noticia_id;
	}
}
