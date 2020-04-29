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
import service.ComentarioService;

/**
 * Servlet implementation class ListarNoticiasController
 */
@WebServlet("/ListarComentarios.do")
public class ListarComentariosController extends HttpServlet {
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
		ComentarioService cs = new ComentarioService();
		List<Comentario> cm = new ArrayList<Comentario>();
		cm = cs.selecionarComentarios(id);
		output.println("<script> "
				+ "$(document).ready(function(){");
		for(Comentario c : cm) {
			String nom = "\"<p class=\'text-center text-justify\'>"+ c.getNome() + "</p>\"";
			System.out.println("DEBUG: " + c.getNome());
			String tex = "\"<p class=\'text-center text-justify\'>"+ c.getTexto() + "</p>\"";
			output.println("$('#NomeComentado').append(" + nom +");");
			output.println("$('#TextoComentado').append(" + tex +");");
		}
		output.println("});");
		output.println("</script>");
		
		request.getRequestDispatcher("ViewNoticia.html").include(request,  response);
	}

}
