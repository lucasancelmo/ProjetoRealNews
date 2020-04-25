package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request,  response);	
		out.println("Noticia cadastrada<br>");
		out.println("Titulo " + til+ "<br>");
		out.println("Descricao " + desc + "<br>");
		out.println("Texto: " + tex+ "<br>");
			
	}

}
