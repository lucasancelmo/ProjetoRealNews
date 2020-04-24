package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import model.Comentario;
import model.Pais;

public class ComentarioDAO {

private Connection conexao;
	
	public void ComentarioDAO (Connection conexao) {
		this.conexao = conexao;
	}
	
	
	public int inserirComentario (Comentario comentario) {
		
		String inserir = "INSERT INTO comentario (nome, texto) VALUES(?, ?)";
		try(PreparedStatement pst = conexao.prepareStatement(inserir)) {
			pst.setString(1, comentario.getNome());
			pst.setString(2, comentario.getTexto());
			pst.execute();
			System.out.println("Comentario inserido com sucesso");
			
			String sqlId = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement smt = conexao.prepareStatement(sqlId);
					ResultSet rs = smt.executeQuery();){
				if(rs.next()) {
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
		
		try(PreparedStatement pst = conexao.prepareStatement(sqlUpdate)) {
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
		String sqlUpdate = "DELETE FROM comentario WHERE id=?";
		
		try(PreparedStatement pst = conexao.prepareStatement(sqlUpdate)) {
			pst.setInt(1, id);
			pst.execute();
			System.out.println("Delete do comentario feito com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao deletar o comentario");
			e.printStackTrace();
		}
	}
	
	public Comentario SelectComentario(int id) {
		String sqlUpdate = "SELECT id,nome, texto FROM comentario WHERE id = ? ";
		
		try(PreparedStatement pst = conexao.prepareStatement(sqlUpdate)){
			pst.SetInt(1,id);
			ResultSet resultado = pst.executeQuery();
			Comentario comentario = null;
			if(resultado.next()) {
				comentario = new Comentario();
				
				int idComentario = resultado.getInt("id");
				String nome = resultado.getString("nome");
				String texto = resultado.getString("texto");
				
				
				//Atribuição
				comentario.setId(idComentario);
				comentario.setNome(nome);
				comentario.setTexto(texto);
				
				
				return comentario;
			}
			System.out.println("Consulta feita com sucesso");
		}catch(Exception e) {
			System.out.println("Erro ao consultar o comentarioo");
			e.printStackTrace();
		}
	}
}
