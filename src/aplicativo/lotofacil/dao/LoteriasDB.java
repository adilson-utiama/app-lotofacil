package aplicativo.lotofacil.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

import aplicativo.lotofacil.jdbc.ConcursosDB;
import aplicativo.lotofacil.util.Utilitarios;

public class LoteriasDB {

	private ConcursosDB concursos;

	public LoteriasDB(){
		
	}

	public void importarDadosParaObjeto() {
		concursos = new ConcursosDB();
		try {
			Scanner entrada = new Scanner(new FileInputStream("csv/loteria.csv"));
			String linha = null;
			while (entrada.hasNextLine()) {
				linha = entrada.nextLine();
				if (!linha.matches(",,,,,,,,,,,,,,")) {
					String sortNumeros = Utilitarios.sortNumeros(linha);
					concursos.adiciona(sortNumeros);
				}
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		concursos.mostrarConcursos();
	}
	
	public void gravarObjetoDB() {
		gravarObjetoDB(this.concursos);
	}

	public void gravarObjetoDB(ConcursosDB banco) {
		try {
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(new FileOutputStream("db/banco.ser"));
			oos.writeObject(banco);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void executarBackup(ConcursosDB banco) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("backup/banco.ser"));
			oos.writeObject(banco);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public ConcursosDB lerObjetoDB() {
		ObjectInputStream ois;
		try {
			//FIXME Corrigir falha na leitura do banco ao executar jar
			
			ois = new ObjectInputStream(new FileInputStream("db/banco.ser"));
			ConcursosDB concursoDB = (ConcursosDB) ois.readObject();
			
			ois.close();
			return concursoDB;
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Falha ao ler Banco\n" + e);
		}
		return null;
	}

	public boolean confereResultado(String jogo){
		if(concursos.isPresente(jogo)) return true;
		return false;
	}
	
	
	
	
	
	
	
	
}
