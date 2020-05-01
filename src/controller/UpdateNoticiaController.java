package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;

import model.Noticia;
import service.NoticiaService;

/**
 * Servlet implementation class UpdateNoticiaController
 */
@WebServlet("/UpdateNoticia.do")
public class UpdateNoticiaController extends HttpServlet {
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
		String til = request.getParameter("titulo");
		String desc = request.getParameter("descricao");
		String tex = StringEscapeUtils.escapeHtml4(request.getParameter("texto"));
		int id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("text/html");
		
		Noticia n = new Noticia();
		n.setId(id);
		n.setTitulo(til);
		n.setDescricao(desc);
		n.setTexto(tex);
		
		NoticiaService ns = new NoticiaService();
		ns.updateNoticia(n);
		
		String rd = "ViewNoticia.do?id=" + id;
		request.getRequestDispatcher(rd).include(request,  response);
		
	}

}
