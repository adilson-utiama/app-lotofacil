package aplicativo.lotofacil.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aplicativo.lotofacil.dao.LoteriasDB;
import aplicativo.lotofacil.jdbc.ConcursosDB;

public class LotoFacilUtil {
	
	private LoteriasDB db;
	private ConcursosDB database = new ConcursosDB();
	private LotoFacilFiltros filtro;
	
	
	public LotoFacilUtil(){
		
			carregar();
			
		
	}
	
	private void carregar() {
		db = new LoteriasDB();
		database = db.lerObjetoDB();
		filtro = new LotoFacilFiltros();
		
	}
	
	public void reloadDB(){
		carregar();
	}

	/**
	 * Gera Jogo Aleatorio
	 * @param dezenas
	 * @return List com Jogo Aleatorio Ordenado
	 */
	public List<Integer> gerarJogoAleatorio(int dezenas){
		List<Integer> random = new LinkedList<>();
		for (int i = 0; i < dezenas; i++) {
			int n01 = (int) (1 + Math.random() * 25);
			if (random.contains(n01)) {
				do {
					n01 = (int) (1 + Math.random() * 25);
				} while (random.contains(n01));
				random.add(n01);
			} else {
				random.add(n01);
			}
		}
		Collections.sort(random);
		return random;
	}
	
	
	/**
	 * Gera jogo com numeros aleatorios de forma generico
	 * @param dezenas
	 * Quantidade de dezenas a sortear
	 * @param limite
	 * Numero maximo a sortear
	 * @return
	 */
	public List<Integer> gerarJogoAleatorioGenerico(int dezenas, int limite){
		List<Integer> random = new LinkedList<>();
		for (int i = 0; i < dezenas; i++) {
			int n01 = (int) (1 + Math.random() * limite);
			if (random.contains(n01)) {
				do {
					n01 = (int) (1 + Math.random() * limite);
				} while (random.contains(n01));
				random.add(n01);
			} else {
				random.add(n01);
			}
		}
		Collections.sort(random);
		return random;
	}

	/**
	 * Metodo para converter uma string para uma List
	 * 
	 * @param string
	 * @return List<Integer>
	 */
 	public List<Integer> toList(String string) {
		List<Integer> jogo = new LinkedList<>();
		String[] line = string.split(" ");
		for (String numero : line) {
			jogo.add(Integer.parseInt(numero));
		}
		return jogo;
	}

 	
	
	/**
	 * Compara duas Colecoes de List
	 * @param jogoA
	 * @param jogoB
	 * @return int 
	 * quantidade de numeros repetidos nas colecoes.
	 */
	public int frequenciaLista(List<Integer> jogoA, List<Integer> jogoB){
		int frequencia = 0;
		for (Integer numero : jogoA) {
			if(jogoB.contains(numero)){
				frequencia++;
			}
		}
		return frequencia;
	}

	/**
	 * Gera saida formatada da List
	 * @param jogo
	 * @return string formatada
	 */
	public String listaParaString(List<Integer> jogo){
		StringBuilder saida = new StringBuilder();
		for (Integer numero : jogo) {
			if(numero < 10){
				saida.append("0" + String.valueOf(numero) + " ");
			}else if(numero == jogo.get(14)){
				saida.append(String.valueOf(numero));
			}else{
				saida.append(String.valueOf(numero) + " ");
			}
			
		}
		
		return saida.toString();
	}
	
	public List<Integer> stringParaLista(String jogo){
		List<Integer> lista = new ArrayList<>();
		String[] split = jogo.split(" ");
		for (String numero : split) {
			lista.add(Integer.parseInt(numero));
		}
		Collections.sort(lista);
		return lista;
	}
	
	public void pegaUltimosConcursos(int quant, ConcursosDB banco){
		
		for (int i = banco.concursosCadastrados(); i > banco.concursosCadastrados() - quant; i--) {
			System.out.println(banco.exibir(i));
		}
		
	}
	
	/**
	 * Retorna a frequencia de numeros a partir do ultimo concurso cadastrado.
	 * @param List<Integer> Jogo a comparar
	 * @return int - Frequencia de números repetidos do último Concurso.
	 */
	public int getRepetidos(List<Integer> jogo){
		int concurso = database.concursosCadastrados();
		String string = database.exibir(concurso);
		List<Integer> lista = stringParaLista(string);
		int frequencia = frequenciaLista(jogo, lista);
		return frequencia;
	}
	
	public List<Integer> getConcurso(int index){
		String concurso = database.exibir(index);
		List<Integer> lista = stringParaLista(concurso);
		return lista;
	}

	public int getUltimoConcurso() {
		int cadastrados = database.concursosCadastrados();
		return cadastrados;
	}

	
	public Map<Integer, Set<Integer>> getFrequenciaNumerosEm(int concursos){
		
		Map<Integer, Set<Integer>> mapa = new HashMap<>();
		for(int i = 1; i <= concursos; i++){
			mapa.put(i, new HashSet<Integer>());
		}
		List<Integer> listaNumeros = new ArrayList<>();
		int cadastrados = database.concursosCadastrados();
		
		for(int i = cadastrados; i > cadastrados - concursos; i--){
			String string = database.exibir(i);
			String[] numeros = string.split(" ");
			for (String n : numeros) {
				listaNumeros.add(Integer.parseInt(n));
			}
			
		}
		for (Integer num : listaNumeros) {
			int frequency = Collections.frequency(listaNumeros, num);
			mapa.get(frequency).add(num);
		}
		
		return mapa;
		
	}

	public List<Integer> gerarRandomJogoComFiltro(Integer dezenas, Integer par, 
			Integer limiteMinimo, Integer limiteMaximo, Integer rep) {
		boolean pares;
		boolean total;
		boolean repetidos = false;
		List<Integer> jogoAleatorio;
		List<Integer> concurso = getConcurso(database.concursosCadastrados() - 1);
		do{
			jogoAleatorio = gerarJogoAleatorio(dezenas);
			pares = filtro.validaPares(jogoAleatorio, par);
			total = filtro.validaSomaTotal(jogoAleatorio, limiteMinimo, limiteMaximo);
			
			int freq = frequenciaLista(jogoAleatorio, concurso);
			
			if(freq > rep){
				repetidos = true;
			}else{
				repetidos = false;
			}
			
			
		}while(pares == false || total == false || repetidos == true);
		
		//System.out.println(frequenciaLista(jogoAleatorio, concurso));
		
		return jogoAleatorio;
	}
	
	
}
