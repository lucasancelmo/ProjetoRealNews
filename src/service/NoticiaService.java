package service;

import dao.NoticiaDAO;
import model.Noticia;
import java.util.ArrayList;
import java.util.List;

public class NoticiaService {
	
	NoticiaDAO dao = new NoticiaDAO();
	
	public Noticia criar (Noticia noticia) {
		return dao.inserirNoticia(noticia);
	}
	
	public void atualizar (Noticia noticia) {
		dao.updateNoticia(noticia);
	}
	
	public void delete (Noticia noticia) {
		dao.deleteNoticia(noticia);
	}
	
	public Noticia selecionar (int id) {
		return dao.selecionarNoticia(id);
	}
	
	public List<Noticia> ListaSelecionar (){
		return dao.selecionarNoticias();
	}
	
}
