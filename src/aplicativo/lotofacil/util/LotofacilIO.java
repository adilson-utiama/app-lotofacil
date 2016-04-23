package aplicativo.lotofacil.util;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import javax.swing.JFileChooser;

import aplicativo.lotofacil.model.Jogo;

public class LotofacilIO {

	private JFileChooser fileChooser = new JFileChooser();
	private PrintStream lista;

	public LotofacilIO() {

	}

	public void salvarLista(List<Jogo> jogos) {

		int returnValue = fileChooser.showSaveDialog(null);
				
		if (returnValue == JFileChooser.APPROVE_OPTION) {

			try {
				lista = new PrintStream(fileChooser.getSelectedFile() + ".lista");
				for (Jogo jogo : jogos) {
					adiciona(jogo);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	private void adiciona(Jogo jogo) {
		lista.println(jogo.getJogo());

	}
	
	
		
		
	}


