package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {
	public static void main(String[] args) {

		// atualizar o salario de um vendedor
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			// comanda para fazer uma atualização
			st = conn.prepareStatement(
					"UPDATE seller " + "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(DepartmentId = ?)");

			st.setDouble(1, 200.0);
			st.setInt(2, 2);

			// executeUpdate sempre que fomos fazer uma atualização
			int rowAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rowAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
