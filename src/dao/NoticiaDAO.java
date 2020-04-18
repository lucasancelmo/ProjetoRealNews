package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Noticia;

public class NoticiaDAO {
	Connection conexao = ConnectionFactory.conectar();
	
	public int inserir(Noticia noticia) {
		
		String inserir = "INSERT INTO noticia (descricao, titulo, texto) VALUES(?, ?, ?)";
		try(PreparedStatement pst = conexao.prepareStatement(inserir)) {
			pst.setString(1, noticia.getDescricao());
			pst.setString(2, noticia.getTitulo());
			pst.setString(3, noticia.getTexto());
			pst.execute();
			
			String sqlId = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement smt = conexao.prepareStatement(sqlId);
					ResultSet rs = smt.executeQuery();){
				if(rs.next()) {
					noticia.setId(rs.getInt(1));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticia.getId();
	}
}
