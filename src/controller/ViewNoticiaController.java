package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comentario;
import model.Noticia;
import service.ComentarioService;
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
		
		
		String til = "\"<h1 class=\'page-header text-center\'>" + noticia.getTitulo() + "</h1><hr>\"";
		String tex = "\"<p class=\'text-center text-justify\'>"+ noticia.getTexto() + "</p>\"";

		output.println("<script src=\"js/jquery-3.5.0.min.js\"></script>");
		
		//Funcao jQuery para adicionar o texto na página HTML no elemento selecionado
		output.println("<script> "
				+ "$(document).ready(function(){"
				+ "$('#titulo').append(" + til +");"
						+ "$('#texto').append("+ tex +");"
								+ "$('#form').attr('id'," + id + ")"
						+ "});"
				+ "</script>");

		ComentarioService cs = new ComentarioService();

		List<Comentario> cm = new ArrayList<Comentario>();
		cm = cs.selecionarComentarios(id);
		if(cm.size() > 0) {
			output.println("<script> "
				+ "$(document).ready(function(){");
		

			for(Comentario c : cm) {
				String nom = "\"<p class=\'text-left text-justify\'>"+ c.getNome() + "</p>\"";
			//	String tex2 = "\"<p class=\'text-left text-justify\'>"+ c.getTexto() + "</p><hr>\"";
				String tex3 = "\"" +c.getTexto() + "\"" + "+" + "\"<hr>\"";
				output.println("$('#Comentarios').append(" + nom + "+" + tex3 +");");
			//	output.println("$('#TextoComentado').append(" + tex2 +");");

			}
			output.println("});");
			output.println("</script>");
		}
		
		
		
		
		
		request.getRequestDispatcher("ViewNoticia.html").include(request,  response);
	}

}
