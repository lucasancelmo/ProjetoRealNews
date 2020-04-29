package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comentario;

public class ComentarioDAO {

	Connection conexao = ConnectionFactory.conectar();

	public int inserirComentario(Comentario comentario) {

		String inserir = "INSERT INTO comentario (nome, texto, fk_noticia_id) VALUES(?, ?, ?)";
		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {
			pst.setString(1, comentario.getNome());
			pst.setString(2, comentario.getTexto());
			pst.setInt(3, comentario.getFk_noticia_id());
			pst.execute();
			System.out.println("Comentario inserido com sucesso");

			String sqlId = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement smt = conexao.prepareStatement(sqlId); ResultSet rs = smt.executeQuery();) {
				if (rs.next()) {
					comentario.setId(rs.getInt(1));
				}

			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir comentario");
			e.printStackTrace();

		}
		return comentario.getId();
	}

	public void atualizarComentario(Comentario comentario) {
		String sqlUpdate = "UPDATE comentario SET nome=?, texto=?,  WHERE id=?";

		try (PreparedStatement pst = conexao.prepareStatement(sqlUpdate)) {
			pst.setString(1, comentario.getNome());
			pst.setString(2, comentario.getTexto());
			pst.execute();
			System.out.println("Update do comentario feito com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o comentario");
			e.printStackTrace();
		}
	}

	public void DeleteComentario(int id) {
		String sqlUpdate = "DELETE FROM comentario WHERE fk_noticia_id=?";

		try (PreparedStatement pst = conexao.prepareStatement(sqlUpdate)) {
			pst.setInt(1, id);
			pst.execute();
			System.out.println("Delete do comentario feito com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao deletar o comentario");
			e.printStackTrace();
		}
	}

	public Comentario SelectComentario(int id) {
		String sqlUpdate = "SELECT id, nome, texto, fk_noticia_id FROM comentario WHERE id = ? ";
		Comentario comentario = new Comentario();
		try (PreparedStatement pst = conexao.prepareStatement(sqlUpdate)) {
			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();

			if (resultado.next()) {
				comentario = new Comentario();

				int idComentario = resultado.getInt("id");
				String nome = resultado.getString("nome");
				String texto = resultado.getString("texto");
				int fk = resultado.getInt("fk_noticia_id");
				// Atribuição
				comentario.setId(idComentario);
				comentario.setNome(nome);
				comentario.setTexto(texto);
				comentario.setFk_noticia_id(fk);

			}
			System.out.println("Consulta feita com sucesso");

		} catch (Exception e) {
			System.out.println("Erro ao consultar o comentarioo");
			e.printStackTrace();
		}
		return comentario;

	}
//
	public List<Comentario> selecionarComentarios(int idNoticia) {
		List<Comentario> list = new ArrayList<Comentario>();
		String selecao = "SELECT id, nome, texto, fk_noticia_id FROM comentario WHERE fk_noticia_id = ?";
		try (PreparedStatement pst = conexao.prepareStatement(selecao);) {
			pst.setInt(1, idNoticia);
			pst.execute();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Comentario c = new Comentario();
				c.setId(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setTexto(rs.getString(3));
				c.setFk_noticia_id(rs.getInt(4));
				list.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
