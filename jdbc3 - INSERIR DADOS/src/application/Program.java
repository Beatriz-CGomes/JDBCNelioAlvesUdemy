package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		// Inserir dados no banco de dados
		// vamos inserir um dados no seller

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {

			// aqui conectando com banco de dados
			conn = DB.getConnection();

			st = conn.prepareStatement(
					// aqui vai ser passado o comando para inserção de daos
					"INSERT INTO seller" + "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES "
							+ "(?, ?, ?, ?, ?)",

					// vamos puxar o ID do dados que acabamos de inserir
					Statement.RETURN_GENERATED_KEYS);

			// fazer o comando ? para ser inserido com dados que quisermos digitar
			st.setString(1, "Theo Gomes");
			st.setString(2, "thego@gmail.com");
			// usando o sql pq estamos inserindo uma data no banco
			st.setDate(3, new java.sql.Date(sdf.parse("02/04/2016").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);

			// EXAMPLE 2 PARA MOSTRAR QUE DA PARA INSERIR DOIS VALORES :
			// st = conn.prepareStatement(
			// "insert into department (Name) values ('Sports'),('Bed Table and Bath')",
			// Statement.RETURN_GENERATED_KEYS);

			// quando for para alterar os dados chamamos executeUpdate para executar
			// int rowAffected aqui para sabermos quantas linhas foram afetadas(alteradas)
			int rowAffected = st.executeUpdate();

			// aqui estavamos imprimindo as linhas inseridas com comando acima
			// System.out.println("Done! Rows Affected: " + rowAffected);

			// mostrando na tela a chave que foi gerada
			if (rowAffected > 0) {

				// esse comando getGeneratedKeys recebe um ResultSet
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}

			} else {
				System.out.println("No rown affected");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {

			DB.closeStatement(st);
			// fechando o banco de dados
			DB.closeConnection();
		}

	}

	// prepareStatement é uma função em Java que nos ajuda a
	// executar consultas ou atualizações em um banco de dados de forma segura e
	// eficiente.
	// precisamos passar alguns valores para consulta
	// O prepareStatement nos permite escrever uma instrução SQL com espaços
	// reservados (geralmente representados por ?) no lugar onde os valores
	// variáveis devem estar. Depois, podemos atribuir esses valores usando métodos
	// específicos. Dessa forma, o banco de dados sabe exatamente o que esperar e
	// como tratar os dados, o que torna nossa consulta mais segura contra ataques
	// de injeção de SQL.

}
