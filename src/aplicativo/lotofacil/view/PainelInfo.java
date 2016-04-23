package aplicativo.lotofacil.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aplicativo.lotofacil.model.Jogo;
import aplicativo.lotofacil.util.LotoFacilUtil;

public class PainelInfo extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private LotoFacilUtil util;
	private JLabel ultimoConcurso;
	private JLabel ultimoConcursoValue;
	private JLabel resultado;
	private JLabel repetidos;
	private JLabel repetidosValue;
	
	private JButton refresh;
	
	private JPanel freq10;
	private JPanel freq5;
	
	private Font fonte = new Font(Font.SERIF, Font.BOLD, 20);

	
	
	public PainelInfo(){
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		
		initComponents();
		initListeners();
		
		setUltimoConcurso();
		
		add(ultimoConcurso);
		add(ultimoConcursoValue);
		add(resultado);
		add(repetidos);
		add(repetidosValue);
		add(freq10);
		add(freq5);
		add(refresh);
	}

	private void initListeners() {
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				util.reloadDB();
				removeLabels();
				setUltimoConcurso();
				popula10();
				popula5();
							
			}
		});
	}

	private void initComponents() {
		util = new LotoFacilUtil();
		
		ultimoConcurso = new JLabel("Ultimo Concurso Cadastrado: ");
		ultimoConcursoValue = new JLabel();
		ultimoConcursoValue.setForeground(Color.RED);
		ultimoConcursoValue.setFont(fonte);
		resultado = new JLabel();
		resultado.setForeground(Color.BLUE);
		repetidos = new JLabel("Repetidos do ultimo Concurso: ");
		repetidosValue = new JLabel();
		repetidosValue.setForeground(Color.RED);
		repetidosValue.setFont(fonte);
		
		refresh = new JButton("Atualizar Dados");
		
		freq10 = new JPanel();
		freq10.setLayout(new GridLayout(10,0));
		freq10.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1,
		       1, 1, Color.BLACK), "Mais sairam em 10 Concursos: "));
		
		freq5 = new JPanel();
		freq5.setLayout(new GridLayout(5,0));
		freq5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1,
		       1, 1, Color.BLACK), "Mais sairam em 5 Concursos: "));
		
		popula10();
		popula5();
		
		initSizes();
	}

	private void initSizes() {
		ultimoConcurso.setBounds(15, 5, 180, 30);
		ultimoConcursoValue.setBounds(190, 3, 100, 30);
		resultado.setBounds(15, 30, 300, 30);
		repetidos.setBounds(15, 60, 180, 30);
		repetidosValue.setBounds(195,58,100,30);
		freq10.setBounds(10, 100, 260, 200);
		freq5.setBounds(10, 310, 260, 100);
		refresh.setBounds(50, 420, 150, 30);
		
	}
	
	
	public void setRepetidos(Jogo jogo){
		String string = jogo.getJogo();
		List<Integer> lista = util.stringParaLista(string);
		int repetidos2 = util.getRepetidos(lista);
		repetidosValue.setText(String.valueOf(repetidos2));
	}
	
	public void setUltimoConcurso(){
		int ultimo = util.getUltimoConcurso();
		String res = util.listaParaString(util.getConcurso(ultimo));
		resultado.setText(res);
		ultimoConcursoValue.setText(String.valueOf(ultimo));
	}
	
	public void popula10(){
		Map<Integer, Set<Integer>> map = util.getFrequenciaNumerosEm(10);
		String numeros = null;
		for (int i = 10; i > 0; i--) {
			numeros = map.get(i).toString();
			if(i < 10){
				freq10.add(new JLabel("  0" + i + "x: " + numeros));
			}else{
				freq10.add(new JLabel("  " + i + "x: " + numeros));
			}
			
		}
	}
	
	public void popula5(){
		Map<Integer, Set<Integer>> map = util.getFrequenciaNumerosEm(5);
		String numeros = null;
		for (int i = 5; i > 0; i--) {
			numeros = map.get(i).toString();
			freq5.add(new JLabel("  0" + i + "x: " + numeros));
			
		}
	}
	
	private void removeLabels(){
		freq10.removeAll();
		freq5.removeAll();
	}
	
	
}
