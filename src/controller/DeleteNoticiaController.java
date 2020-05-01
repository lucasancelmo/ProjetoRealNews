package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comentario;
import service.ComentarioService;
import service.NoticiaService;

/**
 * Servlet implementation class DeleteNoticiaController
 */
@WebServlet("/DeleteNoticia.do")
public class DeleteNoticiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		NoticiaService ns = new NoticiaService();
		
		ComentarioService cs = new ComentarioService();
		//Deletar todos os comentarios da noticia antes de deletar a noticia
		List<Comentario> cm = new ArrayList<Comentario>();
		cm = cs.selecionarComentarios(id);
		if(cm.size() > 0) {
			cs.deleteComentario(id);
		}
		ns.deleteNoticia(id);
		
		response.sendRedirect("index.html");
	}

}
