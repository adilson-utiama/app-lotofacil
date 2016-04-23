package aplicativo.lotofacil.jdbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConcursosDB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Integer, String> concursos = new LinkedHashMap<>();
	public Map<Integer, String> getConcursos() {
		return concursos;
	}

	private int numero = 1;

	public void adiciona(String resultado) {
		this.concursos.put(this.numero, resultado);
		numero++;
	}
	
	public void removeD(Integer concurso){
		this.concursos.remove(concurso);
	}

	public void remove(Integer concurso) {
		this.concursos.replace(concurso, "");
	}

	public void editar(Integer concurso, String resultado) {
		this.concursos.replace(concurso, resultado);
	}

	public void mostrarConcursos() {
		for (int i = 0; i < concursos.size(); i++) {
			System.out.println("Concurso_" + (i + 1) + " = "
					+ concursos.get(i + 1));
		}
	}

	public String exibir(Integer concurso) {
		return concursos.get(concurso);
	}
	
	

	public boolean isPresente(String jogo) {
		for (int i = 0; i < concursos.size(); i++) {
			String sorteio = concursos.get(i + 1);
			List<Integer> jogoB = toList(jogo);
			List<Integer> jogoA = toList(sorteio);
			if (jogoA.containsAll(jogoB))
				return true;
			jogoA.clear();
			jogoB.clear();
		}
		return false;
	}
	
	public boolean isPresente(List<Integer> jogoB) {
		for (int i = 0; i < concursos.size(); i++) {
			String sorteio = concursos.get(i + 1);
			//List<Integer> jogoB = toList(jogo);
			List<Integer> jogoA = toList(sorteio);
			if (jogoA.containsAll(jogoB))
				return true;
			jogoA.clear();
			//jogoB.clear();
		}
		return false;
	}

	private List<Integer> toList(String string) {
		List<Integer> jogo = new LinkedList<>();
		String[] a = string.split(" ");
		Arrays.sort(a);
		for (String num : a) {
			jogo.add(Integer.parseInt(num));
		}
		return jogo;
	}

	public int concursosCadastrados() {
		return concursos.size();
	}

	
	public int jogosRepetidos() {
		int count = 0;
		for (int i = 1; i < concursos.size() + 1; i++) {
			String sta = concursos.get(i);
			List<Integer> jogoA = toList(sta);
			for (int j = 1; j < concursos.size() + 1; j++) {
				String stb = concursos.get(j);
				List<Integer> jogoB = toList(stb);
				if (jogoA.containsAll(jogoB)) {
					if (i != j) {
						count++;
						System.out.println("jogo: " + i + " = " + j);
					}
				}
				jogoB.clear();
			}
			jogoA.clear();
		}
		return count;
	}
	
	public int frequenciaNumeros(int posicao, List<Integer> jogo){
		int frequencia = 0;
		
			String sta = concursos.get(posicao);
			List<Integer> concurso = toList(sta);
			for (Integer integer : jogo) {
				if(concurso.contains(integer)){
					frequencia++;
				}
			}
		
		
		return frequencia;
	}
	
	public String primeiroNumero(){
		int n1 = 0;int n2 = 0;int n3 = 0;int n4 = 0;int n5 = 0;
		
		for (int i = 0; i < concursos.size(); i++) {
			String numero = concursos.get(i + 1);
			List<Integer> list = toList(numero);
			switch(list.get(0)){
			case 1:
				n1++;
				break;
			case 2:
				n2++;
				break;
			case 3:
				n3++;
				break;
			case 4:
				n4++;
				break;
			case 5:
				n5++;
				break;
			}
		}
		return  "1: " + n1 + " 2: " + n2 + " 3: " + n3 + " 4: " + n4 + " 5: " + n5;
	}
	
	public String ultimoNumero(){
		int n21 = 0;int n22 = 0;int n23 = 0;int n24 = 0;int n25 = 0;
		
		for (int i = 0; i < concursos.size(); i++) {
			String numero = concursos.get(i + 1);
			List<Integer> list = toList(numero);
			switch(list.get(14)){
			case 21:
				n21++;
				break;
			case 22:
				n22++;
				break;
			case 23:
				n23++;
				break;
			case 24:
				n24++;
				break;
			case 25:
				n25++;
				break;
			}
		}
		return  "21: " + n21 + " 22: " + n22 + " 23: " + n23 + " 24: " + n24 + " 25: " + n25;
	}
	
	public boolean verificaAcertoEmConcursos(int valor, List<Integer> jogo){
		int frequencia = 0;
		for(int i = 1; i <= concursos.size(); i++){
			String string = this.concursos.get(i);
			String[] numeros = string.split(" ");
			List<Integer> conc = converter(numeros);
			for (Integer integer : jogo) {
				if(conc.contains(integer)){
					frequencia++;
				}
			}
			if(frequencia > valor){
				//System.out.println(i);
				return true;
			}
			frequencia = 0;
			
		}
		return false;
	}

	private List<Integer> converter(String[] numeros) {
		List<Integer> jogo = new ArrayList<>();
		for (String string : numeros) {
			jogo.add(Integer.parseInt(string));
		}
		return jogo;
	}

}
