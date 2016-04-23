package aplicativo.lotofacil.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class TelaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane abas;
	private JScrollPane js;
	private JPanel fullPanel;
	private JPanel contMenuTop;
	private JPanel contPrincipal;
	private JPanel contMenuBottom;
	
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension tela = tk.getScreenSize();
	private Dimension espaco;
	
	public TelaPrincipal(){
		super();
		this.setTitle("Lotofacil Util");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		espaco = new Dimension((int)tela.getWidth(), (int)tela.getHeight());
		this.setSize(espaco);
		
		initLayouts();
		initComponents();
	}


	private void initLayouts() {
		
			
		fullPanel = new JPanel();
		fullPanel.setPreferredSize(espaco);
		js = new JScrollPane(fullPanel);
		
				
		abas = new JTabbedPane();
				
		contPrincipal = new JPanel(new BorderLayout());
		contPrincipal.setBackground(Color.LIGHT_GRAY);
		contPrincipal.add(abas, BorderLayout.CENTER);
		
		contMenuTop = new JPanel();
		contMenuTop.setBackground(Color.LIGHT_GRAY);
		
		contMenuBottom = new JPanel();
		contMenuBottom.setBackground(Color.LIGHT_GRAY);
		
		initComponentsSizes();
				
		Container container = getContentPane();	
		container.setLayout(new BorderLayout());
		
		fullPanel.setLayout(null);
		fullPanel.add(contMenuTop);
		fullPanel.add(contMenuBottom);
		fullPanel.add(contPrincipal);
		
		container.add(js, BorderLayout.CENTER);
				
		abas.addTab("Painel Jogos", new PainelJogos(contPrincipal.getSize()));
		abas.addTab("Painel Banco", new PainelBancoDados(contPrincipal.getSize()));
		abas.addTab("Painel Conferir", new PainelConfere(contPrincipal.getSize()));
		abas.addTab("Simulacao", new PainelSimulacao(contPrincipal.getSize()));
	}


	


	private void initComponentsSizes() {
		
		double alturaMenuTop = espaco.getHeight() * 0.04;
		
		contMenuTop.setBounds(0, 0, (int)espaco.getWidth(), (int)alturaMenuTop);
		contPrincipal.setBounds(0, (int) alturaMenuTop, (int)espaco.getWidth(), (int)(espaco.getHeight() - (alturaMenuTop * 2) - 35));
		contMenuBottom.setBounds(0, (int)(espaco.getHeight() - (alturaMenuTop * 2)), (int)espaco.getWidth(), (int)alturaMenuTop);
	
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
	}

}
