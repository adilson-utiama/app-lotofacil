package aplicativo.lotofacil.model;

public class Jogo {

	private int id;
	private String jogo;
	
	public Jogo(String jogo){
		this.jogo = jogo;
				
	}
		
	public Jogo(int id,String jogo){
		this.jogo = jogo;
		this.id = id;
		
	}
	
	public String getJogo() {
		return jogo;
	}

	public int getPares() {
		int par = verificaPares();
		return par;
	}

	
	public int getSomaTotal() {
		int soma = verificaSoma();
		return soma;
	}

	private int verificaSoma() {
		int s = 0;
		String[] numeros = this.jogo.split(" ");
		for (String n : numeros) {
			s += Integer.parseInt(n);
		}
		return s;
	}

		
	private int verificaPares() {
		int p = 0;
		String[] numeros = this.jogo.split(" ");
		for (String n : numeros) {
			if(Integer.parseInt(n) % 2 == 0){
				p++;
			}
		}
		return p;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
}
