package controller;

import java.io.IOException;
import java.io.PrintWriter;
import model.Noticia;
import service.NoticiaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// LUCAS ESTOU ALTERANDO ESSE SERVLET PQ CRIEI A SERVICE ACREDITO QUE SEJA ASSIM QUE FAÇA
		
		// Instaciamento da Noticia 
		Noticia noticia = new Noticia();
		noticia.setTitulo(til);
		noticia.setDescricao(desc);
		noticia.setTexto(tex);
		
		// instaciamento da Service
		NoticiaService cs = new NoticiaService();
		cs.criar(noticia);
		noticia = cs.selecionar(noticia.getId());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request,  response);	
		out.println("Noticia cadastrada<br>");
		out.println("Titulo " + noticia.getTitulo() + "<br>");
		out.println("Descricao " + noticia.getDescricao() + "<br>");
		out.println("Texto: " + noticia.getTexto() + "<br>");
			
	}

}
