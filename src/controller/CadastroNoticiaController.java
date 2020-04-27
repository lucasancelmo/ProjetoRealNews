package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;

/**
 * Servlet implementation class CadastroNoticiaController
 */
@WebServlet("/CadastroNoticia.do")
public class CadastroNoticiaController extends HttpServlet {
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
		String tex = request.getParameter("texto");
		response.setContentType("text/html");
		
		//Instanciar javabean
		Noticia noticia = new Noticia();
		noticia.setTitulo(til);
		noticia.setDescricao(desc);
		noticia.setTexto(tex);
		//Instanciar Service
		
		NoticiaService ns = new NoticiaService();
		noticia = ns.selecionarNoticia(ns.inserir(noticia));
		PrintWriter out = response.getWriter();

		
		
		//Código para testar resposta apenas
		
		/*out.println("<html><head><title>Cadastro de Noticia</title>"
				+ "<script src=\"js/jquery-3.5.0.min.js\"></script>"
				+ "</head><body>");
		
		out.println("Id:" + noticia.getId() + "<br>"
				+ "Titulo: " + noticia.getTitulo() + "<br>"
						+ "Descricao:" + noticia.getDescricao()+"<br>"
								+ "Texto: "+ noticia.getTexto()+"<br> ");
		out.println("<script> "
				+ "$(document).ready(function(){"
				+ "$('#results').append(\"<li>"+ til +"</li>\");"
						+ "});"
				+ "</script>");
		out.println("<div class=container>"
				+ "<ol id=results></ol>"
				+ "</div>");

		out.println("</body></html>");*/
		request.getRequestDispatcher("CadastroNoticia.html").include(request,  response);
			
	}

}
