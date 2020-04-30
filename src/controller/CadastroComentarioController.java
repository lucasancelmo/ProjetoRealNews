package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ComentarioService;
import model.Comentario;

@WebServlet("/CadastroComentario.do")
public class CadastroComentarioController extends HttpServlet {
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
		String nome = request.getParameter("nome");
		System.out.println("Peguei o nome " + nome);
		String tex = request.getParameter("texto");
		System.out.println("Peguei o texto " + tex);
		int idFk = Integer.parseInt(request.getParameter("id"));
		response.setContentType("text/html");
		
		// Instanciar JavaBean
		
		Comentario comentario = new Comentario();
		comentario.setNome(nome);
		comentario.setTexto(tex);
		comentario.setFk_noticia_id(idFk);
		// Instanciar Service
		
		ComentarioService cs = new ComentarioService();
		comentario = cs.consultaComentario(cs.inserirComentario(comentario));
	//	PrintWriter out = response.getWriter();
		
		//Código para testar resposta apenas
		
				/*out.println("<html><head><title>Cadastro de Noticia</title>"
						+ "<script src=\"js/jquery-3.5.0.min.js\"></script>"
						+ "</head><body>");
				
				out.println("Id:" + comentario.getId() + "<br>"
										+ "Texto: "+ comentario.getTexto()+"<br> ");
				out.println("<script> "
						+ "$(document).ready(function(){"
						+ "$('#results').append(\"<li>"+ tex +"</li>\");"
								+ "});"
						+ "</script>");
				out.println("<div class=container>"
						+ "<ol id=results></ol>"
						+ "</div>");

				out.println("</body></html>");*/
		
		String rd = "ViewNoticia.do?id=" + 1;
		System.out.println("Passei por aqui");
		request.getRequestDispatcher(rd).include(request,  response);
	}

}
