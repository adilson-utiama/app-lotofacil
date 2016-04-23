package aplicativo.lotofacil.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import aplicativo.lotofacil.jdbc.DataBaseLoteriaMySQL;
import aplicativo.lotofacil.util.Utilitarios;

public class LoteriasMySQL {

	private Connection connection = DataBaseLoteriaMySQL.getConnection();

	public void importarDadosParaBancoMySQL() {

		try {
			// Connection connection = DataBaseLoteriaMySQL.getConnection();
			String sql = "INSERT INTO concursos (resultado) values(?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			Scanner entrada = new Scanner(new FileInputStream("loteria.csv"));
			String linha = null;
			while (entrada.hasNextLine()) {
				linha = entrada.nextLine();
				if (!linha.matches(",,,,,,,,,,,,,,")) {
					String sortNumeros = Utilitarios.sortNumeros(linha);
					statement.setString(1, sortNumeros);
					statement.execute();
				}
			}
			statement.close();
			connection.close();
			entrada.close();
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void adiciona(String resultado){
		String sql = "INSERT INTO concursos (resultado) values(?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, resultado);
			statement.execute();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
