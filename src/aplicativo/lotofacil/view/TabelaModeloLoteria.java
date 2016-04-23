package aplicativo.lotofacil.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicativo.lotofacil.model.Jogo;

public class TabelaModeloLoteria extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Jogo> jogos;
	private Jogo jogo;
	private String[] colunas = {"Jogo","Pares","Soma"};
	
	public TabelaModeloLoteria() {
		jogos = new ArrayList<>();
		
	}
	
	public Jogo getJogo(int linha){
		return jogos.get(linha);
	}
	
	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}
	
	@Override
	public int getRowCount() {
		return jogos.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna){
			case 0:
				return jogos.get(linha).getJogo();
			case 1:
				return jogos.get(linha).getPares();
			case 2:
				return jogos.get(linha).getSomaTotal();
			default :
				return jogos.get(linha);
		}
		
	}

	public void adicionaJogo(Jogo jogo) {
		jogos.add(jogo);
		fireTableDataChanged();
		
	}
	
	public void limparTabela(){
		jogos.clear();
		fireTableDataChanged();
	}

	public void deletarJogo(int linha) {
		jogos.remove(linha);
		fireTableDataChanged();
		
	}

	public List<Jogo> getAll() {
		return jogos;
		
	}

}
