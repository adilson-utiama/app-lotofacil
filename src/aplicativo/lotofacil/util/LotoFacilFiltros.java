package aplicativo.lotofacil.util;

import java.util.Collections;
import java.util.List;

public class LotoFacilFiltros {

	public boolean validaPares(List<Integer> jogo, int limite){
		if(getPares(jogo) > limite){
			return true;
		}
		return false;
	}
	
	

	public boolean validaSomaTotal(List<Integer> jogo, int limiteMinimo, int limiteMaximo){
		int somaTotal = getSomaTotal(jogo);
		if(somaTotal < limiteMinimo || somaTotal > limiteMaximo){
			return true;
		}
		return false;
	}
	
	public int getPares(List<Integer> jogo) {
		int pares = 0;
		for (Integer numero : jogo) {
			if(numero % 2 == 0){
				pares++;
			}
		}
		return pares;
	}
	
	public int getSomaTotal(List<Integer> jogo){
		int somaTotal = 0;
		for (Integer numero : jogo) {
			somaTotal+=numero;
		}
		return somaTotal;
	}
	
	public static void main(String[] args) {
		LotoFacilUtil util = new LotoFacilUtil();
		List<Integer> jogoAleatorio = util.gerarJogoAleatorio(15);
		LotoFacilFiltros filtros = new LotoFacilFiltros();
		boolean b = filtros.validaSomaTotal(jogoAleatorio, 175, 210);
		boolean c = filtros.validaPares(jogoAleatorio, 9);
		boolean rep = filtros.validaRepetidos(jogoAleatorio);
//		System.out.println(filtros.getSomaTotal(jogoAleatorio));
//		System.out.println(b);
//		System.out.println(filtros.getPares(jogoAleatorio));
//		System.out.println(c);
//		System.out.println(c && b);
		System.out.println(rep);
	}



	public boolean validaRepetidos(List<Integer> aleatorio) {
		LotoFacilUtil util = new LotoFacilUtil();
		int concurso = util.getUltimoConcurso();
		System.out.println(concurso);
		List<Integer> ultimoConcurso = util.getConcurso(concurso);
		System.out.println(ultimoConcurso);
		System.out.println(aleatorio);
		int repetidos = 0;
		for (Integer integer : ultimoConcurso) {
			repetidos += Collections.frequency(aleatorio, integer);
		}
		
		System.out.println(repetidos);
		return repetidos > 10 ? true : false;
	}
	
}
