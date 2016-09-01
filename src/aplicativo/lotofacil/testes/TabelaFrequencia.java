package aplicativo.lotofacil.testes;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aplicativo.lotofacil.util.BancoFactory;
import aplicativo.lotofacil.util.LotoFacilUtil;

public class TabelaFrequencia {

	
	private Map<String, Set<Integer>> tabela;
	private LotoFacilUtil util = new LotoFacilUtil();
	private List<String> ultimosConcursos;
	
	
	public TabelaFrequencia() {
		ultimosConcursos = util.getUltimosConcursos(10, BancoFactory.getBanco());
	}
	
	public List<String> getUltimosConcursos() {
		return ultimosConcursos;
	}
	
	public Map<String, Set<Integer>> getFrequenciaTabela(int concursos){
		tabela = new HashMap<>();
		for (int i = 0; i <= concursos; i++) {
			tabela.put(String.valueOf(i), new HashSet<Integer>());
		}
		ultimosConcursos = util.getUltimosConcursos(concursos, BancoFactory.getBanco());
		for (int i = 1; i <= 25; i++) {
			int frequency = Collections.frequency(ultimosConcursos, i);
			tabela.get(String.valueOf(frequency)).add(i);
		}
		
		return tabela;
	}
	
	
	public static void main(String[] args) {
		TabelaFrequencia tb = new TabelaFrequencia();
		Map<String, Set<Integer>> map = tb.getFrequenciaTabela(5);
		for (int i = map.size() - 1; i >= 0; i--) {
			System.out.print(i + " = ");
			System.out.println(map.get(String.valueOf(i)));
		}
	}
	
}
