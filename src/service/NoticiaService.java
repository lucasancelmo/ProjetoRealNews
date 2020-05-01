package service;

import java.util.List;

import dao.NoticiaDAO;
import model.Noticia;

public class NoticiaService {
	NoticiaDAO dao = new NoticiaDAO();
	public int inserir(Noticia noticia) {
		return dao.inserirNoticia(noticia);
	}
	
	public void deleteNoticia(int id) {
		dao.deleteNoticia(id);
	}
	public void updateNoticia(Noticia noticia) {
		dao.updateNoticia(noticia);
	}
	public Noticia selecionarNoticia(int id) {
		return dao.selecionarNoticia(id);
	}
	public List<Noticia> selecionarNoticias(){
		return dao.selecionarNoticias();
	}
}
