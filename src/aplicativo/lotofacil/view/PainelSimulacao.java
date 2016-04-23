package aplicativo.lotofacil.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import aplicativo.lotofacil.util.LotoFacilUtil;

public class PainelSimulacao extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension tamanho;
	private JTextArea status;
	private JPanel painelEsquerdo;
	private JPanel painelCentral;
	private JPanel painelDireito;
	private JButton simular;
	private double parte;
	
	private JLabel numerosGerados;
	private JLabel freq11, freq12, freq13, freq14, freq15;
	
	private LotoFacilUtil util;

	public PainelSimulacao(Dimension tamanho){
		this.tamanho = tamanho;
		setLayout(new BorderLayout());
		
		initLayout();
		
		add(painelEsquerdo, BorderLayout.WEST);
		add(painelCentral, BorderLayout.CENTER);
		add(painelDireito, BorderLayout.EAST);
	}

	private void initLayout() {
		parte = tamanho.getWidth() / 5;
		painelEsquerdo = new JPanel();
		painelEsquerdo.setBackground(Color.GREEN);
		painelEsquerdo.setPreferredSize(new Dimension((int)parte, (int)tamanho.getHeight()));
		painelCentral = new JPanel();
		painelCentral.setLayout(null);
		painelCentral.setBackground(Color.YELLOW);
		painelCentral.setPreferredSize(new Dimension((int)(parte * 3), (int)tamanho.getHeight()-100));
		painelDireito = new JPanel();
		painelDireito.setBackground(Color.CYAN);
		painelDireito.setPreferredSize(new Dimension((int)parte, (int) tamanho.getHeight()));
		
		initComponents();
		initListeners();
		
		painelCentral.add(status);
		painelCentral.add(simular);
		painelCentral.add(numerosGerados);
		painelCentral.add(freq11);
		painelCentral.add(freq12);
		painelCentral.add(freq13);
		painelCentral.add(freq14);
		painelCentral.add(freq15);
	}

	private void initComponents() {
		util =  new LotoFacilUtil();
		status = new JTextArea();
		status.setOpaque(true);
		
		status.setBackground(Color.GREEN);
		simular = new JButton("Simular!");
		
		numerosGerados = new JLabel("Numeros Gerados");
		freq11 = new JLabel("11 acertos: ");
		freq12 = new JLabel("12 acertos: ");
		freq13 = new JLabel("13 acertos: ");
		freq14 = new JLabel("14 acertos: ");
		freq15 = new JLabel("15 acertos: ");
		
		initSizes();
	}

	private void initSizes() {
		status.setBounds(50, 50, 450, 400);
		simular.setBounds(250, 450, 250, 50);
		numerosGerados.setBounds(50, 500, 300, 30);
		freq11.setBounds(50, 550, 200, 30);
		freq12.setBounds(50, 580, 200, 30);
		freq13.setBounds(50, 610, 200, 30);
		freq14.setBounds(50, 640, 200, 30);
		freq15.setBounds(50, 670, 200, 30);
	}
	
	private void initListeners() {
		simular.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				//new ImplementacaoRunnable(numerosGerados).start();	
				
				
				Runnable r = new Runnable() {
					
					@Override
					public void run() {
						
						List<Integer> jogoAleatorio = new ArrayList<Integer>();
						int frequencia = 0;
						int total = 0;
						
						numerosGerados.setText("Jogos gerados: ");
						freq11.setText("11 acertos: ");
						freq12.setText("12 acertos: ");
						freq13.setText("13 acertos: ");
						freq14.setText("14 acertos: ");
						freq15.setText("15 acertos: ");
						
						int f11 = 0,f12 = 0, f13 = 0, f14 = 0, f15 = 0;
						int ultimoConcurso = util.getUltimoConcurso();
						List<Integer> concurso = util.getConcurso(ultimoConcurso);
						
						do {
							
							//jogoAleatorio = util.gerarJogoAleatorio(15);
							jogoAleatorio = util.gerarRandomJogoComFiltro(15,9,175,210, 10);
							numerosGerados.setText("Jogos gerados: " + ++total);
							
							frequencia = util.frequenciaLista(jogoAleatorio, concurso);
						
						
						switch(frequencia){
						case 11: freq11.setText("11 acertos: " + (++f11)); break;
						case 12: freq12.setText("12 acertos: " + (++f12)); break;
						case 13: freq13.setText("13 acertos: " + (++f13)); break;
						case 14: freq14.setText("14 acertos: " + (++f14)); break;
						case 15: freq15.setText("15 acertos: " + (++f15)); break;
						}
						
					
						
						}while(frequencia != 15);
						
					}
				};
				
				new Thread(r).start();
				
			}
			
			
			
		});
		
	}
	
	
	
}
