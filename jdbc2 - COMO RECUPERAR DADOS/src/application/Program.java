package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {
	public static void main(String[] args) {

		// Vamos fazer uma recuperação no banco de dados - recuperando o departamento

		// conectar o banco
		Connection conn = null;
		// preparando uma consulta com banco
		Statement st = null;
		// guardando o resultado no rs
		ResultSet rs = null;

		try {

			// aqui conectando o banco de dados
			conn = DB.getConnection();

			// aqui estamos instanciando um objeto do tipo Statement
			st = conn.createStatement();

			// o metado o espera que entra com uma string que é o comando do sql
			rs = st.executeQuery("select * from department");
			// resultado da consulta atribuimos no rs.

			// o rs é o objeto com a tabela do banco de dados

			// o next retorna algo
			while (rs.next()) {
				// aqui estamos acessando e pegando pelo o campo no banco de dados
				// o getInt e getString é pq os campos Id é numero inteiro e name é uma String
				System.out.println(rs.getInt("Id") + ", " + rs.getString("name"));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			// metado da clase DB para fechamento do st e rs pois são comandos do sql
			DB.closeResultSet(rs);
			DB.closeStatement(st);

			// fechando a conecção com banco de dados
			DB.getConnection();
		}
	}

}
