package aplicativo.lotofacil.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aplicativo.lotofacil.model.Jogo;
import aplicativo.lotofacil.util.LotoFacilFiltros;
import aplicativo.lotofacil.util.LotoFacilUtil;
import aplicativo.lotofacil.util.LotofacilIO;

public class PainelBotoesJogos extends JPanel{

	private static final long serialVersionUID = 1273370082165996503L;

	private TabelaModeloLoteria modelo;
	private LotoFacilUtil util;
	private LotoFacilFiltros filtros;
	private LotofacilIO files;
	
	private JLabel quant;
	private JSpinner quantCounter;
	private SpinnerNumberModel quantCounterModel;
	
	private JLabel pares;
	private JSpinner paresCounter;
	private SpinnerNumberModel paresCounterModel;
	
	private JLabel soma;
	private JSpinner somaMinimo;
	private JLabel min;
	private SpinnerNumberModel somaMinimoModel;
	private JLabel max;
	private JSpinner somaMaximo;
	private SpinnerNumberModel somaMaximoModel;
	
	private JButton gerarAleatorio;
	private JButton gerar;
	private JButton limpar;
	private JButton salvar;
	private JButton manual;
	
	
	public PainelBotoesJogos(TabelaModeloLoteria modelo){
		this.modelo = modelo;
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		initComponents();
		
		add(quant);
		add(quantCounter);
		add(pares);
		add(paresCounter);
		add(soma);
		add(min);
		add(somaMinimo);
		add(max);
		add(somaMaximo);
		
		add(gerarAleatorio);
		add(gerar);
		add(limpar);
		add(salvar);
		add(manual);
		
	}


	private void initComponents() {
		util = new LotoFacilUtil();
		filtros = new LotoFacilFiltros();
		files = new LotofacilIO();
		
		quant = new JLabel("  Quantidade de Jogos: ");
		quant.setOpaque(true);
		quant.setBackground(Color.GRAY);
		quant.setForeground(Color.WHITE);
		quantCounterModel = new SpinnerNumberModel(1, 1, 100, 1);
		quantCounter = new JSpinner(quantCounterModel);
		quantCounter.setFocusable(false);
		
		pares = new JLabel("  Máximo de Pares");
		pares.setOpaque(true);
		pares.setBackground(Color.GRAY);
		pares.setForeground(Color.WHITE);
		paresCounterModel = new SpinnerNumberModel(6, 2, 12, 1);
		paresCounter = new JSpinner(paresCounterModel);
		
		soma = new JLabel(" Soma Total");
		soma.setOpaque(true);
		soma.setBackground(Color.GRAY);
		soma.setForeground(Color.WHITE);
		somaMinimoModel = new SpinnerNumberModel(170, 150, 245, 1);
		min = new JLabel("  Minimo: ");
		somaMinimo = new JSpinner(somaMinimoModel);
		somaMaximoModel = new SpinnerNumberModel(210, 150, 245, 1);
		max = new JLabel(" Máximo: ");
		somaMaximo =  new JSpinner(somaMaximoModel);
		
		gerarAleatorio = new JButton("Gerar (Sem Filtro)");
		gerar = new JButton("Gerar (Com Filtro)");
		limpar = new JButton("Limpar");
		salvar = new JButton("Salvar Lista");
		manual = new JButton("Manual");
		
		initSizes();
		initListeners();
	}


	private void initListeners() {
		gerarAleatorio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int quantidade = Integer.parseInt(quantCounter.getValue().toString());
				for (int i = 0; i < quantidade; i++) {
					List<Integer> aleatorio = util.gerarJogoAleatorio(15);
					String saida = util.listaParaString(aleatorio);
					Jogo jogo = new Jogo(saida);
					modelo.adicionaJogo(jogo);
				}
				
			}
		});
		
		gerar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int quantidade = Integer.parseInt(quantCounter.getValue().toString());
				int maximoPares = Integer.parseInt(paresCounter.getValue().toString());
				System.out.println(maximoPares);
				int minimo = Integer.parseInt(somaMinimo.getValue().toString());
				System.out.println(minimo);
				int maximo = Integer.parseInt(somaMaximo.getValue().toString());
				System.out.println(maximo);
				
				for(int i = 0; i < quantidade; i++){
					List<Integer> aleatorio;
					boolean pares = false;
					boolean somaTotal = false;
					boolean repetidos = false;
					
					do{
						aleatorio = util.gerarJogoAleatorio(15);
						pares = filtros.validaPares(aleatorio, maximoPares);
						somaTotal = filtros.validaSomaTotal(aleatorio, minimo, maximo);
						repetidos = filtros.validaRepetidos(aleatorio);
					}while(pares || somaTotal || repetidos);
					
					String saida = util.listaParaString(aleatorio);
					Jogo jogo = new Jogo(saida);
					modelo.adicionaJogo(jogo);
				}
				
				
			}
		});
		
		somaMinimo.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int minimo = Integer.parseInt(somaMinimo.getValue().toString());
				int maximo = Integer.parseInt(somaMaximo.getValue().toString());
				if(minimo >= maximo){
					somaMinimo.setValue(maximo - 1);
				}
				
			}
		});
		
		somaMaximo.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int minimo = Integer.parseInt(somaMinimo.getValue().toString());
				int maximo = Integer.parseInt(somaMaximo.getValue().toString());
				if(maximo <= minimo){
					somaMaximo.setValue(maximo + 1);
				}
				
			}
		});
		
		limpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.limparTabela();
				
			}
		});
	
		salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Jogo> all = modelo.getAll();
				int option = JOptionPane.showConfirmDialog(null, "Salvar Lista?");
				if(option != 0 || all.isEmpty()){
					JOptionPane.showMessageDialog(null, "Lista Vazia! Nada foi salvo...");
				}else{
					files.salvarLista(all);
				}
				
			
			}
		});
	
		manual.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				new PainelDeAdicionarNumerosManual(modelo).setVisible(true);
				
			}
			
		});
	}


	private void initSizes() {
		quant.setBounds(10, 10, 250, 30);
		quantCounter.setBounds(10, 45, 50, 30);
			
		pares.setBounds(10, 85, 250, 30);
		paresCounter.setBounds(10, 120, 50, 30);
		
		soma.setBounds(10, 155, 250, 30);
		min.setBounds(10, 190, 70, 30);
		somaMinimo.setBounds(70, 190, 50, 30);
		max.setBounds(130, 190, 70, 30);
		somaMaximo.setBounds(190, 190, 50, 30);
		
		gerar.setBounds(10, 280, 150, 40);
		gerarAleatorio.setBounds(10, 330, 150, 40);
		limpar.setBounds(10, 380, 150, 40);
		salvar.setBounds(10, 430, 150, 40);
		manual.setBounds(10, 480, 150, 40);
		
	}
}
