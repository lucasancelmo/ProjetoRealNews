package service;

import java.util.List;

import dao.ComentarioDAO;
import model.Comentario;

public class ComentarioService {
	ComentarioDAO dao = new ComentarioDAO();
	
	public int inserirComentario(Comentario comentario) {
		return dao.inserirComentario(comentario);
	}
	
	public List<Comentario> selecionarComentarios(int idNoticia){
		
		return dao.selecionarComentarios(idNoticia);
	}
	
	public Comentario consultaComentario(int id) {
		return dao.SelectComentario(id);
	}
	public void deleteComentario(int id) {
		dao.DeleteComentario(id);
	}
	
}
