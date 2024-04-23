package application;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Program {
	public static void main(String[] args) {

		// TRANSAÇÃO DE DADOS
		Connection conn = null;
		Statement st = null;

		try {

			conn = DB.getConnection();
			// aqui dentro o comando para fazer a transição

			// aqui faz uma segurança na transação
			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE departmentId = 1");

			// simulando falha no meio da transação
			int x = 1;
			if (x < 2) {
				throw new SQLException("FAKE ERROR! ");
			}

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE departmentId = 2");

			// aqui executa tudo ou não executa nada
			conn.commit();

			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);

		} catch (SQLException e) {
			try {
				// volta caso de erro em algum momento da transação, roda tudo ou não roda nada
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} // esse erro é gerado caso de um erro no rollback
			catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {

			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}
