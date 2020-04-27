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
 * Servlet implementation class ListarNoticiasController
 */
@WebServlet("/ViewNoticia.do")
public class ViewNoticiaController extends HttpServlet {
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
		PrintWriter output = response.getWriter();
		
		
		Noticia noticia = new Noticia();
		NoticiaService ns = new NoticiaService();
		noticia = ns.selecionarNoticia(id);
		
		//
		String til = "\"<h1 class=\'page-header text-center\'>" + noticia.getTitulo() + "</h1><hr>\"";
		String tex = "\"<p class=\'text-center text-justify\'>"+ noticia.getTexto() + "</p>\"";
//		output.println("<html><head><title>Noticia</title>");
		output.println("<script src=\"js/jquery-3.5.0.min.js\"></script>");
		/*output.println("<div class=container>"
				+ "Titulo: " + noticia.getTitulo() +" <br> "
						+ "Texto: "+ noticia.getTexto()+ ""
				+ "</div>");*/
		
		//Funcao jQuery para adicionar o texto na página HTML no elemento selecionado
		output.println("<script> "
				+ "$(document).ready(function(){"
				+ "$('#titulo').append(" + til +");"
						+ "$('#texto').append("+ tex +");"
						+ "});"
				+ "</script>");
//		output.println("</body></html>");
		
		request.getRequestDispatcher("ViewNoticia.html").include(request,  response);
	}

}
