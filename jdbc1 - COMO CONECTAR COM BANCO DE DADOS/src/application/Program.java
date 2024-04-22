package application;

import java.sql.Connection;

import db.DB;

public class Program {
	public static void main(String[] args) {

		//aqui roda a aplicação com banco de dados
		Connection conn = DB.getConnection();
		
		//aqui fecha a conexao com banco de dados
		DB.closeConnection();

	}

}
