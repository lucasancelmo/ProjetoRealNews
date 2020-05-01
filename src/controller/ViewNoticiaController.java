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

import org.apache.commons.text.StringEscapeUtils;

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
		
		String escape = StringEscapeUtils.escapeHtml4(noticia.getTexto());
		
		String til = "\"<h1 id=\'tituloview\' class=\'page-header text-center\'>" + noticia.getTitulo() + "</h1><hr>\"";
		String desc = "\"<p id=\'descview\' hidden>"+ noticia.getDescricao() + "</p>\"";
		
		String tex ="`<p id=\'textoview\' class=\'text-center text-justify\'>"+ escape + "</p>`";
		
		String form = "\"<form action=\'CadastroComentario.do' method=\'get\' accept-charset=utf-8><div class=\'form-group w-25\'><label for=\'titulo\'>Nome</label> <input type=\'text\'	class=\'form-control\' id=\'nome\' name=\'nome\'	placeholder=\'Digite o seu nome\' required><input type=\'hidden\' class='form-control' id=\'id\' name=\'id\' value= "+ id + "></div><hr><div class=\'form-group\'><label for=\'descricao\'>Comentario</label><textarea class=\'form-control\' id=\'titulo\' rows=\'3\' name=\'texto\' placeholder=\'Digite o seu comentario\' required></textarea></div><div class=\'text-right\'><button type=\'submit\' class=\'btn btn-dark\'>Salvar comentario</button></div></form>\"";
		
		output.println("<script src=\"js/jquery-3.5.0.min.js\"></script>");
		
		String hiddenId = "\"<input type=\'hidden\' class='form-control' id=\'id\' name=\'id\' value= "+ id + ">\"";

		
		//Funcao jQuery para adicionar o texto na página HTML no elemento selecionado
		output.println("<script> "
				+ "$(document).ready(function(){"
				+ "$('#titulo').append(" + til +");"
				+ "$('#texto').append("+ tex +");"
				+ "$('#texto').append(" + desc + ");"
				+ "$('#form').append(" + form + ");"
				+ "$('#exampleModalLong').css('display', 'block');"
				+ "$('#updateId').append("+ hiddenId+ ");"
				+ "$('#excluir').append("+ hiddenId+ ");"
				+ "$('#update').attr('action', 'UpdateNoticia.do');"
				+ "$('#exampleModalLong').css('display', 'none');"
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
				String tex3 = "\"" +c.getTexto() + "\"" + "+" + "\"<hr>\"";
				output.println("$('#Comentarios').append(" + nom + "+" + tex3 +");");

			}
			output.println("});");
			output.println("</script>");
		}
		
		
		
		
		
		request.getRequestDispatcher("ViewNoticia.html").include(request,  response);
	}

}
