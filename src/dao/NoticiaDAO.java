package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Noticia;

public class NoticiaDAO {
	Connection conexao = ConnectionFactory.conectar();

	public int inserirNoticia(Noticia noticia) {

		String inserir = "INSERT INTO noticia (descricao, titulo, texto) VALUES(?, ?, ?)";
		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {
			pst.setString(1, noticia.getDescricao());
			pst.setString(2, noticia.getTitulo());
			pst.setString(3, noticia.getTexto());
			pst.execute();

			String sqlId = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement smt = conexao.prepareStatement(sqlId); ResultSet rs = smt.executeQuery();) {
				if (rs.next()) {
					noticia.setId(rs.getInt(1));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticia.getId();
	}

	public void deleteNoticia(int id) {
		String delete = "DELETE FROM noticia WHERE id = ?";
		try (PreparedStatement pst = conexao.prepareStatement(delete)) {
			pst.setInt(1, id);
			pst.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Noticia selecionarNoticia(int id) {
		String selecionar = "SELECT id, descricao, titulo, texto FROM noticia WHERE id = ?";
		Noticia noticia = new Noticia();

		try (PreparedStatement pst = conexao.prepareStatement(selecionar)) {
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				noticia.setId(rs.getInt("id"));
				noticia.setDescricao(rs.getString("descricao"));
				noticia.setTitulo(rs.getString("titulo"));
				noticia.setTexto(rs.getString("texto"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticia;
	}

	public void updateNoticia(Noticia noticia) {
		String update = "UPDATE noticia SET descricao=?, titulo=?, texto=? WHERE id =?";
		try (PreparedStatement pst = conexao.prepareStatement(update)) {
			pst.setString(1, noticia.getDescricao());
			pst.setString(2, noticia.getTitulo());
			pst.setString(3, noticia.getTexto());
			pst.setInt(4, noticia.getId());
			pst.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Noticia> selecionarNoticias() {
		List<Noticia> list = new ArrayList<Noticia>();
		String selecao = "SELECT id, titulo, descricao, texto FROM noticia";
		try (PreparedStatement pst = conexao.prepareStatement(selecao); ) {
			pst.execute();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Noticia n = new Noticia();
				n.setId(rs.getInt(1));
				n.setTitulo(rs.getString(2));
				n.setDescricao(rs.getString(3));
				n.setTexto(rs.getString(4));
				list.add(n);
			}
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
