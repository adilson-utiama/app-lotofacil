package aplicativo.lotofacil.main;

import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;

public class Teste {

	public static void main(String[] args) {
		
		System.load("banco.ser");
		
		URL url = Toolkit.getDefaultToolkit().getClass().getResource("/banco.ser");
		String path = url.getPath();
		System.out.println(path);
	}
}
