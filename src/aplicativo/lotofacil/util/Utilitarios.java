package aplicativo.lotofacil.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utilitarios {

	public static String sortNumeros(String linha) {
		StringBuilder builder = new StringBuilder();
		List<Integer> resultado = new LinkedList<>();
		String line = null;
		
		String[] numeros = linha.split(",");
		for (String string : numeros) {
			resultado.add(Integer.parseInt(string));
		}
		Collections.sort(resultado);
		builder = new StringBuilder();
		for (Integer num : resultado) {
			if (num <= 9) {
				builder.append("0" + num + " ");
			} else {
				builder.append(num + " ");
			}

		}
		line = builder.toString();
		return line;

	}
}
