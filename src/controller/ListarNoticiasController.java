package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.NoticiaService;
import model.Noticia;

/**
 * Servlet implementation class ListarNoticiasController
 */
@WebServlet("/ListarNoticiasController")
public class ListarNoticiasController extends HttpServlet {
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
		NoticiaService ns = new NoticiaService();
		List<Noticia> ln = new ArrayList<Noticia>();
		ln = ns.selecionarNoticias();
		
		output.println("<script> "
				+ "$(document).ready(function(){");
		for(Noticia n : ln) {
			String til = "\"<p class=\'text-center text-justify\'>"+ n.getTitulo() + "</p>\"";
			String desc = "\"<p class=\'text-center text-justify\'>"+ n.getDescricao() + "</p>\"";
			output.println("$('#til').append(" + til +");");
			output.println("$('#desc').append(" + desc +");");
		}
		output.println("});");
		output.println("</script>");
		
		request.getRequestDispatcher("index.html").include(request,  response);
		
	}

}
