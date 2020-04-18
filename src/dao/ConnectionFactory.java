package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		
		try {
			System.out.println("Buscando Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver encontrado");
		}catch (ClassNotFoundException e) {
			System.out.println("Driver não Encontrado");
			e.printStackTrace();
		}
	}
	
	public static Connection conectar() {
		
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/portal_realnews?useTimezone=true&serverTimezone=America/Sao_Paulo&",		
					"root", "");
			System.out.println("Conexão Estabelecida com Sucesso");
			return c;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
