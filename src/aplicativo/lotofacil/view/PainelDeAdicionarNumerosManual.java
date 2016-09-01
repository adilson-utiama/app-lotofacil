package aplicativo.lotofacil.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicativo.lotofacil.model.Jogo;
import aplicativo.lotofacil.util.LotoFacilUtil;

public class PainelDeAdicionarNumerosManual extends JFrame{


	private static final long serialVersionUID = 1L;
	TecladoDigital tecladoDigital = new TecladoDigital(250, 250, 15);
	JButton clear = new JButton("Clear");
	JButton addNumber = new JButton("Add Number");
	
	JPanel painel = new JPanel(); 
	private TabelaModeloLoteria modelo;
	
	private LotoFacilUtil util = new LotoFacilUtil();
	
	public PainelDeAdicionarNumerosManual(TabelaModeloLoteria modelo){
		this.modelo = modelo;
		
		setSize(300, 450);
		setLayout(new BorderLayout());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setAlwaysOnTop(true);
		setFocusable(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		painel.setLayout(new GridLayout(2,2));
		painel.setPreferredSize(new Dimension(100, 100));
		
		painel.add(addNumber);
		painel.add(clear);
		
		add(tecladoDigital, BorderLayout.CENTER);
		add(painel, BorderLayout.SOUTH);
		//add(addNumber, BorderLayout.EAST);
		//add(clear, BorderLayout.WEST);
		
		getContentPane();
		
		addNumber.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Integer> selecionados = tecladoDigital.getNumerosSelecionados();
				if(selecionados.size() < 15){
					JOptionPane.showMessageDialog(null, "Selecione 15 numeros");
				}else{
					String string = util.listaParaString(selecionados);
					modelo.adicionaJogo(new Jogo(string));
					
				}
				selecionados.clear();
				tecladoDigital.limparTela();
				
			}
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tecladoDigital.limparTela();
				
			}
		});
		
	}
	
	public TabelaModeloLoteria getModelo() {
		return modelo;
	}
}
