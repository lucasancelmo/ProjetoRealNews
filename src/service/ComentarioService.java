package service;

import model.Comentario;
import dao.ComentarioDAO;

public class ComentarioService {
	
	ComentarioDAO dao = new ComentarioDAO();
	
	public Comentario criar (Comentario comentario) {
		return dao.inserirComentario(comentario);
	}
	
	public void atualizar (Comentario comentario) {
		dao.atualizarComentario(comentario);
	}
	
	public void excluir (int id) {
		dao.DeleteComentario(id);
	}
	
	public Comentario consultar(int id) {
		return dao.SelectComentario(id);
	}
	
}
