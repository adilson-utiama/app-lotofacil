package aplicativo.lotofacil.testes;

import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;

import aplicativo.lotofacil.util.LotoFacilUtil;

public class Gerador implements Runnable {

	private JLabel label;
	private LotoFacilUtil util = new LotoFacilUtil();
	private List<Integer> jogoB = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
	private int frequenciaLista;
	
	public Gerador(JLabel label){
		this.label = label;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000000; i++) {
			
			do{
			frequenciaLista = 0;
			List<Integer> list = util.gerarJogoAleatorio(15);
			frequenciaLista = util.frequenciaLista(list, jogoB );
			String string = util.listaParaString(list);
			label.setText("Imprimindo: " + string);
			}while(frequenciaLista != 15);
		}

	}

}
