package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {
	public static void main(String[] args) {

		// DELETAR DADOS
		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			// aqui dentro o comando para deletar
			st = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");

			st.setInt(1, 2);

			int rowAffected = st.executeUpdate();

			System.out.println("Done! Rows Affected: " + rowAffected);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}
